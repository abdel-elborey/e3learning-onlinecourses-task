package com.e3learning.onlineeducation.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomCollectionEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.e3learning.onlineeducation.model.Account;
import com.e3learning.onlineeducation.model.Course;
import com.e3learning.onlineeducation.model.Training;
import com.e3learning.onlineeducation.model.TrainingPK;
import com.e3learning.onlineeducation.service.AccountService;
import com.e3learning.onlineeducation.service.CountryService;
import com.e3learning.onlineeducation.service.CourseService;
import com.e3learning.onlineeducation.service.TrainingService;
import com.e3learning.onlineeducation.vo.AccountSearchForm;
import com.e3learning.onlineeducation.vo.EnrollInCourseForm;
 
@Controller 
public class AccountController {
	
	private static final Logger logger = Logger.getLogger(AccountController.class);
	
	@Autowired
	private AccountService accountService;
	@Autowired
	private CourseService courseService;
	@Autowired
	private CountryService countryService;
	@Autowired
	private TrainingService trainingService;
	
	private Map<String, Training> trainingCache;
	
	@RequestMapping(value = "/", method=RequestMethod.GET)
	public String gotoHome() {
		return "index";		
	}
	
	@RequestMapping(value = "/searchAccounts", method=RequestMethod.GET)
	public String gotoSearch() {
		return "search_accounts";		
	} 
		
	@RequestMapping(value = "/searchAccounts", method=RequestMethod.POST)
	public @ResponseBody List<Account> searchAccounts(@RequestBody AccountSearchForm accountSearch) {
		List<Account> accounts = null;
		try {
			accounts = accountService.searchAccounts(accountSearch);
			logger.info("searchAccounts result is " + accounts);
		} catch (Exception e) {
			logger.error(e.getMessage());
			e.printStackTrace();
		}
		return accounts;		
	}
	
	
	@RequestMapping(value = "/getMyCourses/{accountId}", method=RequestMethod.GET)
	public @ResponseBody List<Training> getMyCourses(@PathVariable String accountId) {	
		List<Training> trainings = null;
		try {
			Account account = new Account();
			account.setId(Long.valueOf(accountId));
			trainings = trainingService.findByAccount(account, 0, 10).getContent();
		} catch (NumberFormatException e) {
			logger.error(e.getMessage());
			e.printStackTrace();
		}
		return trainings;
	}
	

	@RequestMapping(value = "/enrollUserInCourse" , method=RequestMethod.POST)
	public @ResponseBody Training enrolUserInCourse(@RequestBody EnrollInCourseForm enrollInCourseForm) {		
		Training training = null;
		try {
			Account account = accountService.findById(enrollInCourseForm.getAccountId());
			Course course = courseService.findById(enrollInCourseForm.getCourseId());			
			training = trainingService.enrollAccountInCourse(account, course, new Date());
		} catch (Exception e) {
			logger.error(e.getMessage());
			e.printStackTrace();
		}
		return training; 
	}
	
	@RequestMapping(value = "/accounts" , method=RequestMethod.GET)
	public String addAccountForm(Model model, HttpServletRequest request) {
		String target = "add_account";
		try {
			Account account = new Account();
			model.addAttribute("account", account);
			
			List<Course> courses = courseService.findAll();
			List<Training> availableTraining = new ArrayList<Training>();
			trainingCache = new HashMap<String,Training>();
			for(Course course : courses){
				Training training = new Training();
				TrainingPK trainingPK = new TrainingPK();
				trainingPK.setCourseId(course.getId());
				training.setCourse(course);
				training.setTrainingPK(trainingPK);
				availableTraining.add(training);
				trainingCache.put(training.getCourse().getId().toString(), training);
			}
			request.getSession().setAttribute("availableTraining", availableTraining);
			
			if(request.getSession().getAttribute("countries") == null)
				request.getSession().setAttribute("countries", countryService.findAll());
		}catch(Exception exception){
			logger.error(exception.getMessage());
			exception.printStackTrace();
			model.addAttribute("message","<font style='color: #ff0000;'>Operation Failed Please Contact administrator or try again later</font>");
			target = "index";
		}
		
		return target;		
	}
	
	@InitBinder
    public void initBinder(WebDataBinder binder) {
     binder.registerCustomEditor(List.class, "training", new CustomCollectionEditor(List.class) {

            protected Object convertElement(Object element) {
                if (element instanceof Training) {
                	return (Training) element;
                } if(element instanceof String){
                	Training training = trainingCache.get(element);                
                	return training;
                } 
                return null;
            }

        });
	}
 
	@RequestMapping(value = "/accounts" , method=RequestMethod.POST)
	public String addAccount(Model model,@Valid @ModelAttribute Account account, BindingResult result){

		String retunPage = "index";
		if(result.hasErrors()){	
			retunPage = "add_account";
		}else{
			try{
				if(account.getTraining() != null){
					for (Training training : account.getTraining()) {
						Course course = courseService.findById(training.getCourse().getId());
						training.setStartDate(new Date());
						training.setCourse(course);
					}
				}
				accountService.saveAccount(account);
				model.addAttribute("message", "Account was Created Successfully");
			}catch(Exception exception){
				exception.printStackTrace();
				logger.error(exception.getMessage());
				model.addAttribute("message","<font style='color: #ff0000;'>Operation Failed Please Contact administrator or try again later</font>");
			}
		}
		return retunPage;
	}

}
