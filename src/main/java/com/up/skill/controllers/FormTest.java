package com.up.skill.controllers;

import com.up.skill.models.TestForm;
import com.up.skill.models.TestRepository;
import com.up.skill.support.web.MessageHelper;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import javax.validation.constraints.AssertFalse;
import java.io.PrintWriter;
import java.util.List;


@Controller
public class FormTest {

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public void setRespInJSON(@ModelAttribute @Valid TestForm testForm,
                                Errors errors,
                                HttpServletResponse response,
                                BindingResult bindingResult) {
        try {
            PrintWriter out = response.getWriter();

            JSONObject obj = new JSONObject();
            //JSONArray fields = new JSONArray();
            //JSONArray errMessages = new JSONArray();

            if (!errors.hasErrors()) {
                obj.put("message", "success");
                testRepository.save(testForm);
            } else {
                List<FieldError> errs = bindingResult.getFieldErrors();

                for (FieldError error : errs) {

                    obj.put(error.getField(), error.getDefaultMessage());
                }
            }
            System.out.println(obj);

            //JSON RESPONSE
            out.print(obj);

        } catch (Exception e) {
            e.toString();
        }
    }

    @Autowired
    TestRepository testRepository;

    @RequestMapping(value = "/sevenEleven")
    public String sevenForm(Model model) {
        model.addAttribute(new TestForm());
        return "sevenElevenHome/index";
    }

    @RequestMapping(value = "/underConstruct")
    public String underConstruct() {
        return "sevenElevenHome/underConstruct";
    }

    @RequestMapping(value = "/home")
    public String home() {
        return "sevenElevenHome/index";
    }

    @RequestMapping(value = "registrant/{id}")
    public String registrant(@PathVariable Long id, Model model) {
        model.addAttribute("user", testRepository.findOne(id));
        return "sevenElevenHome/registrant";
    }

    @RequestMapping(value = "registrants", method = RequestMethod.GET)
    public String userList(Model model) {
        model.addAttribute("users", testRepository.findAll());
        return "sevenElevenHome/registrants";
    }

    @RequestMapping("/update")
    public String formUpdate(long id,
                             @RequestParam String fullname,
                             @RequestParam String email,
                             @RequestParam String mobile,
                             RedirectAttributes ra) {

        TestForm regForm = testRepository.findOne(id);
        regForm.setFullname(fullname);
        regForm.setEmail(email);
        regForm.setMobile(mobile);

        testRepository.save(regForm);

        MessageHelper.addSuccessAttribute(ra, "form.updated");
        return "redirect:/registrants";
    }

    @RequestMapping(value = "deleteNow/{id}")
    public String doDelete(@PathVariable Long id,
                           RedirectAttributes ra){
        testRepository.delete(id);

        MessageHelper.addSuccessAttribute(ra, "form.deleted");
        return "redirect:/registrants";
    }

}