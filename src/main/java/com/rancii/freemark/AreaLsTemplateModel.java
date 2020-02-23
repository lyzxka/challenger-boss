package com.rancii.freemark;

import com.google.common.collect.Lists;
import com.rancii.entity.AreaLs;
import com.rancii.service.AreaLsService;
import freemarker.core.Environment;
import freemarker.template.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import java.util.Map;


/**
 * @Auther: lmk
 * @Date: 2018/11/2 0002 14:33
 * @Description: 城市区域模板
 */
@Component
public class AreaLsTemplateModel extends BaseDirective implements TemplateDirectiveModel {
    @Autowired
    private AreaLsService areaLsService;

    @Override
    public void execute(Environment environment, Map map, TemplateModel[] templateModels, TemplateDirectiveBody templateDirectiveBody) throws TemplateException, IOException {
        Iterator iterator = map.entrySet().iterator();
        List<AreaLs> dictList = Lists.newArrayList();
//        while (iterator.hasNext()) {
//            Map.Entry<String, TemplateModel> param = (Map.Entry<String, TemplateModel>) iterator.next();
//            String paramName = param.getKey();
//            TemplateModel paramValue = param.getValue();
//
//        }
        dictList = areaLsService.list();
        if(dictList.size()<=0){
            throw new TemplateModelException("返回值为空");
        }
        DefaultObjectWrapperBuilder builder = new DefaultObjectWrapperBuilder(Configuration.VERSION_2_3_26);
        environment.setVariable("area_ls", builder.build().wrap(dictList));
        templateDirectiveBody.render(environment.getOut());
    }
}
