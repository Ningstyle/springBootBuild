package com.small.common.generator;

import cn.hutool.core.util.StrUtil;
import com.small.common.generator.bean.Entity;
import lombok.extern.slf4j.Slf4j;
import org.beetl.core.Configuration;
import org.beetl.core.GroupTemplate;
import org.beetl.core.Template;
import org.beetl.core.resource.FileResourceLoader;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

/**
 * 代码生成器 Mybatis-Plus
 * @author Exrickx
 */
@Slf4j
public class MPGenerator {

    /**
     * 实体类名
     * 建议仅需修改
     */
    private static final String className = "RolePermission";

    /**
     * 类说明描述
     * 建议仅需修改
     */
    private static final String description = "角色权限关系";

    /**
     * 作者名
     * 建议仅需修改
     */
    private static final String author = "luhanlin";

    /**
     * 实体类对应包
     * (文件自动生成至该包下)
     */
    private static final String entityPackage = "com.small.user.entity.po";

    /**
     * dao对应包
     * (文件自动生成至该包下)
     */
    private static final String daoPackage = "com.small.user.dao";

    /**
     * service对应包
     * (文件自动生成至该包下)
     */
    private static final String servicePackage = "com.small.user.service";

    /**
     * serviceImpl对应包
     * (文件自动生成至该包下)
     */
    private static final String serviceImplPackage = "com.small.user.service.impl";

    /**
     * controller对应包
     * (文件自动生成至该包下)
     */
    private static final String controllerPackage = "com.small.user.controller";

    /**
     * 运行该主函数即可生成代码
     * @param args
     * @throws IOException
     */
    public static void main(String[] args) throws IOException {
        //模板路径
        String root = System.getProperty("user.dir")+"/src/main/java/com/small/user/common/generator/template";
        FileResourceLoader resourceLoader = new FileResourceLoader(root,"utf-8");
        Configuration cfg = Configuration.defaultConfiguration();
        GroupTemplate gt = new GroupTemplate(resourceLoader, cfg);
        //生成代码
        generateCode(gt);

        //根据类名删除生成的代码
        //deleteCode(className);
    }

    /**
     * 生成代码
     * @param gt
     * @throws IOException
     */
    private static void generateCode(GroupTemplate gt) throws IOException {

        Template serviceTemplate = gt.getTemplate("mpService.btl");
        Template serviceImplTemplate = gt.getTemplate("mpServiceImpl.btl");

        Entity entity = new Entity();
        entity.setEntityPackage(entityPackage);
        entity.setDaoPackage(daoPackage);
        entity.setServicePackage(servicePackage);
        entity.setServiceImplPackage(serviceImplPackage);
        entity.setControllerPackage(controllerPackage);
        entity.setAuthor(author);
        entity.setClassName(className);
        entity.setDescription(description);

        OutputStream out = null;

        //生成service代码
        serviceTemplate.binding("entity",entity);
        String serviceResult = serviceTemplate.render();
        log.info(serviceResult);
        //创建文件
        String serviceFileUrl = System.getProperty("user.dir")+"/src/main/java/"+ dotToLine(servicePackage) + "/"  + className + "Service.java";
        File serviceFile = new File(serviceFileUrl);
        File serviceDir = serviceFile.getParentFile();
        if (!serviceDir.exists()) {
            serviceDir.mkdirs();
        }
        if(!serviceFile.exists()) {
            // 若文件存在则不重新生成
            serviceFile.createNewFile();
            out = new FileOutputStream(serviceFile);
            serviceTemplate.renderTo(out);
        }

        //生成serviceImpl代码
        serviceImplTemplate.binding("entity",entity);
        String serviceImplResult = serviceImplTemplate.render();
        log.info(serviceImplResult);
        //创建文件
        String serviceImplFileUrl = System.getProperty("user.dir")+"/src/main/java/"+ dotToLine(serviceImplPackage) + "/" + className + "ServiceImpl.java";
        File serviceImplFile = new File(serviceImplFileUrl);
        File serviceImplDir = serviceImplFile.getParentFile();
        if (!serviceImplDir.exists()) {
            serviceImplDir.mkdirs();
        }
        if(!serviceImplFile.exists()) {
            // 若文件存在则不重新生成
            serviceImplFile.createNewFile();
            out = new FileOutputStream(serviceImplFile);
            serviceImplTemplate.renderTo(out);
        }

        if(out!=null){
            out.close();
        }
        log.info("生成代码成功！");
    }

    /**
     * 删除指定类代码
     * @param className
     * @throws IOException
     */
    private static void deleteCode(String className) throws IOException {


        String serviceFileUrl = System.getProperty("user.dir")+"/src/main/java/"+ dotToLine(servicePackage) + "/I" +className+"Service.java";
        File serviceFile = new File(serviceFileUrl);
        if(serviceFile.exists()){
            serviceFile.delete();
        }

        String serviceImplFileUrl = System.getProperty("user.dir")+"/src/main/java/"+ dotToLine(serviceImplPackage) + "/I" +className+"ServiceImpl.java";
        File serviceImplFile = new File(serviceImplFileUrl);
        if(serviceImplFile.exists()){
            serviceImplFile.delete();
        }

        String controllerFileUrl = System.getProperty("user.dir")+"/src/main/java/"+ dotToLine(controllerPackage) + "/" +className+"Controller.java";
        File controllerFile = new File(controllerFileUrl);
        if(controllerFile.exists()){
            controllerFile.delete();
        }

        String mapperXmlFileUrl = System.getProperty("user.dir")+"/src/main/resources/mapper/" + className + "Mapper.xml";
        File mapperXmlFile = new File(mapperXmlFileUrl);
        if(mapperXmlFile.exists()){
            mapperXmlFile.delete();
        }

        log.info("删除代码完毕！");
    }

    /**
     * 点转斜线
     * @param str
     * @return
     */
    public static String dotToLine(String str){
        return str.replace(".", "/");
    }

    /**
     * 驼峰法转下划线
     */
    public static String camel2Underline(String str) {
        if (StrUtil.isBlank(str)) {
            return "";
        }
        if(str.length()==1){
            return str.toLowerCase();
        }
        StringBuffer sb = new StringBuffer();
        for(int i=1;i<str.length();i++){
            if(Character.isUpperCase(str.charAt(i))){
                sb.append("_"+Character.toLowerCase(str.charAt(i)));
            }else{
                sb.append(str.charAt(i));
            }
        }
        return (str.charAt(0)+sb.toString()).toLowerCase();
    }

    /**
     * 首字母小写
     */
    public static String first2LowerCase(String str) {
        if (StrUtil.isBlank(str)) {
            return "";
        }
        if(str.length()==1){
            return str.toLowerCase();
        }
        StringBuffer sb = new StringBuffer();
        sb.append(Character.toLowerCase(str.charAt(0)));
        sb.append(str.substring(1,str.length()));
        return sb.toString();
    }
}
