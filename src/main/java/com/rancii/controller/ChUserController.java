package com.rancii.controller;

import com.xiaoleilu.hutool.date.DateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.rancii.entity.ChUser;
import com.rancii.service.ChUserService;
import com.rancii.util.LayerData;
import com.rancii.util.RestResponse;
import com.rancii.annotation.SysLog;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Date;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.WebUtils;

import javax.servlet.ServletRequest;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 用户表  前端控制器
 * </p>
 *
 * @author hantw
 * @since 2020-02-23
 */
@Controller
@RequestMapping("/chUser")
public class ChUserController {
    private static final Logger LOGGER = LoggerFactory.getLogger(ChUserController.class);

    @Autowired
    private ChUserService chUserService;

    @GetMapping("list")
    @SysLog("跳转用户表列表")
    public String list(){
        return "/chUser/list";
    }

    @PostMapping("list")
    @ResponseBody
    @SysLog("请求用户表列表数据")
    public LayerData<ChUser> list(@RequestParam(value = "page",defaultValue = "1")Integer page,
                                      @RequestParam(value = "limit",defaultValue = "10")Integer limit,
                                      ServletRequest request){
        Map map = WebUtils.getParametersStartingWith(request, "s_");
        LayerData<ChUser> layerData = new LayerData<>();
        QueryWrapper<ChUser> wrapper = new QueryWrapper<>();
        wrapper.eq("del_flag",false);
        if(!map.isEmpty()){
            String phone = (String) map.get("phone");
            if(StringUtils.isNotBlank(phone)) {
                wrapper.like("phone",phone);
            }else{
                map.remove("phone");
            }

            String name = (String) map.get("name");
            if(StringUtils.isNotBlank(name)) {
                wrapper.like("name",name);
            }else{
                map.remove("name");
            }

        }
        IPage<ChUser> pageData = chUserService.page(new Page<>(page,limit),wrapper);
        layerData.setData(pageData.getRecords());
        layerData.setCount((int)pageData.getTotal());
        return layerData;
    }

    @GetMapping("add")
    @SysLog("跳转新增用户表页面")
    public String add(){
        return "/chUser/add";
    }

    @PostMapping("add")
    @SysLog("保存新增用户表数据")
    @ResponseBody
    public RestResponse add(ChUser chUser){
        chUserService.save(chUser);
        return RestResponse.success();
    }

    @GetMapping("edit")
    @SysLog("跳转编辑用户表页面")
    public String edit(Long id,Model model){
        ChUser chUser = chUserService.getById(id);
        model.addAttribute("chUser",chUser);
        return "/chUser/edit";
    }

    @PostMapping("edit")
    @ResponseBody
    @SysLog("保存编辑用户表数据")
    public RestResponse edit(ChUser chUser){
        if(null == chUser.getId() || 0 == chUser.getId()){
            return RestResponse.failure("ID不能为空");
        }
        chUserService.updateById(chUser);
        return RestResponse.success();
    }

    @PostMapping("delete")
    @ResponseBody
    @SysLog("删除用户表数据")
    public RestResponse delete(@RequestParam(value = "id",required = false)Long id){
        if(null == id || 0 == id){
            return RestResponse.failure("ID不能为空");
        }
        ChUser chUser = chUserService.getById(id);
        chUser.setDelFlag(true);
        chUserService.updateById(chUser);
        return RestResponse.success();
    }

}