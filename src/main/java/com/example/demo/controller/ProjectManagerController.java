package com.example.demo.controller;

import com.example.demo.controller.Base.BaseController;
import com.example.demo.dao.pojo.Project;
import com.example.demo.service.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping(value = {"/manager/"})
public class ProjectManagerController extends BaseController {
    @Autowired
    private ProjectService projectService;

    @RequestMapping(value = {"/query.htm"})
    public String queryPage() {
        return "manager/list";
    }

    @RequestMapping(value = {"/queryList.htm"},produces = "application/json;charset=UTF-8")
    @ResponseBody
    public List<Project> query() {
        List<Project> projectList = new ArrayList<>();
        try {
            projectList = projectService.selectAll();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return projectList;
    }

    @RequestMapping(value = {"/initadd.htm"})
    public String initadd() {
        return "manager/add";
    }

    @RequestMapping(value = {"/add.htm"})
    public String add(@ModelAttribute("project") Project project) {
        project.setValidity('1');
        try {
            projectService.add(project);
        } catch (Exception e) {
            return "manager/add";
        }
        return "redirect:query.htm";
    }

    @RequestMapping(value = {"/delete.htm"})
    public String remove(@ModelAttribute("pid") String pid) {
        try {
            projectService.delete(Integer.parseInt(pid));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "redirect:query.htm";
    }


}
