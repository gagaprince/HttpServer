package com.prince.server.jsp;

import com.prince.server.http.WebApplication;

import javax.tools.JavaCompiler;
import javax.tools.ToolProvider;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * Created by gagaprince on 16-8-27.
 */
public class CreateJSPFileTest {
    private String br = "\r\n";

    public String giveMeSource(){
        StringBuffer sourceSb = new StringBuffer();
        sourceSb.append("package com.prince.server.jsp.temp;").append(br);
        sourceSb.append("public class test_jsp{").append(br);
        sourceSb.append("   public void out(){").append(br);
        sourceSb.append("       System.out.println(\"这是一个测试输出！\");").append(br);
        sourceSb.append("       System.out.println(\"这是一个测试输出1！\");").append(br);
        sourceSb.append("   }").append(br);
        sourceSb.append("}").append(br);
        return sourceSb.toString();
    }

    public String createSourceFile(){
        String source = giveMeSource();
        System.out.println(source);
//        System.out.println(System.getProperty("user.dir"));
        WebApplication webApplication = WebApplication.getInstance();
        String root = webApplication.getRoot();
        String dir = root+"/jsp/";
        File dirFile = new File(dir);
        if(!dirFile.exists()){
            dirFile.mkdir();
        }
        File sourceFile = new File(dir+"test_jsp.java");
        writeToFile(sourceFile, source);
        return sourceFile.getAbsolutePath();
    }

    public String compiler(String path){
        String classPath = this.getClass().getResource("/").getPath();
        JavaCompiler javaCompiler = ToolProvider.getSystemJavaCompiler();
        int result = javaCompiler.run(null,null,null,"-d",classPath,path);
        System.out.println(classPath);
        System.out.println("result:" + result);
        return "com.prince.server.jsp.temp.test_jsp";
    }

    public void excute(String className){
        try {
            Object jsp = Class.forName(className).newInstance();
            Class jspClass = jsp.getClass();
            Method out = jspClass.getMethod("out");
            out.invoke(jsp);
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
    }

    private void writeToFile(File f,String source){
        try {
            FileOutputStream fos = new FileOutputStream(f);
            fos.write(source.getBytes());
            fos.flush();
            fos.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public static void main(String[] args) {
        CreateJSPFileTest c = new CreateJSPFileTest();
        String filePath = c.createSourceFile();
        String className = c.compiler(filePath);
        c.excute(className);
    }
}
