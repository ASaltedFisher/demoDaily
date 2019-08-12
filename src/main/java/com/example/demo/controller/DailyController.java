package com.example.demo.controller;

import com.alibaba.fastjson.JSONObject;
import com.example.demo.controller.Base.BaseController;
import com.example.demo.dao.pojo.DailyInfo;
import com.example.demo.dao.pojo.DailyInfoPlus;
import com.example.demo.dao.pojo.DailyUser;
import com.example.demo.service.DailyInfoService;
import com.example.demo.util.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.text.SimpleDateFormat;
import java.util.*;
@Controller
@RequestMapping(value = {"/daily/"})
public class DailyController extends BaseController {

    @Autowired
    private DailyInfoService dailyInfoService;



    @RequestMapping(value = {"/save.htm"})
    public String query(HttpServletRequest request, Model model) {
        DailyUser user = (DailyUser) request.getSession().getAttribute(DR_USER);
        String startdate = DateUtils.getCurrentMonday(0);
        Date now = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String enddate = sdf.format(now);
        List<String> markedList = new ArrayList<>();
        try {
            markedList = dailyInfoService.checkDate(startdate, enddate, user.getUid());
        } catch (Exception e) {
            e.printStackTrace();
        }
        List<String> leak = new ArrayList<>();
        for(int i = 0; ; i++) {
            String tempdate = DateUtils.getCurrentMonday(i);
            if(tempdate.equals(enddate)) {
                break;
            } else {
                if(!markedList.contains(tempdate) && DateUtils.holiday(tempdate) != 2) {
                    leak.add(tempdate);
                }
            }
        }
        String message = "";
        if(leak.size() > 0) {
            for(String d : leak) {
                message += d + ", ";
            }
            message = message.substring(0, message.length() - 2);
            message += "的日报未填写，请尽快补填";
        }
        model.addAttribute(ERROR_MSG, message);
        return "user/edit";
    }

    @RequestMapping(value = {"/add.htm"})
    @ResponseBody
    public DailyInfoPlus add(HttpServletRequest request, @RequestParam("jsonStr") String jsonStr) {
        DailyUser user = (DailyUser) request.getSession().getAttribute(DR_USER);
        DailyInfo dailyInfo = JSONObject.parseObject(jsonStr, DailyInfo.class);
        dailyInfo.setUid(user.getUid());
        DailyInfoPlus result = null;
        try {
            dailyInfoService.add(dailyInfo);
            result = dailyInfoService.queryByPK(dailyInfo.getDid());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    @RequestMapping(value = {"/mark.htm"})
    @ResponseBody
    public Map<String, String> mark(HttpServletRequest request) {
        DailyUser user = (DailyUser) request.getSession().getAttribute(DR_USER);
        List<String> dateList = new ArrayList<>();
        try {
            dateList = dailyInfoService.selectMark(user.getUid());
        } catch (Exception e) {
            e.printStackTrace();
        }
        Map<String, String> dateMap = new HashMap<>();
        for (String date : dateList) {
            dateMap.put(date, "");
        }
        return dateMap;
    }

    @RequestMapping(value = {"/getdaily.htm"})
    @ResponseBody
    public List<DailyInfoPlus> getDaily(HttpServletRequest request, String date) {
        List<DailyInfoPlus> dailyInfoList = new ArrayList<>();
        DailyUser user = (DailyUser) request.getSession().getAttribute(DR_USER);
        try {
            Integer[] uid = new Integer[1];
            uid[0] = user.getUid();
            dailyInfoList = dailyInfoService.queryByDate(date, uid);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return dailyInfoList;
    }

    @RequestMapping(value = {"/delete.htm"})
    @ResponseBody
    public boolean delete(String did) {
        int id = Integer.parseInt(did);
        try {
            dailyInfoService.delete(id);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }


}
