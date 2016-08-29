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
public class CreateJSPFile {
    private String br = "\r\n";
    private String tempFilePath = "";
    private String packageName = "";
    private String className = "";
    private String classAllName = "";
    private String classPath = "";
    private String classFilePath = "";
    private String jspFilePath = "";

    public String giveMeSource(String path){
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

    public String createSourceFile(String path){
        String source = giveMeSource(path);
        System.out.println(source);
//        System.out.println(System.getProperty("user.dir"));

        File dirFile = new File(tempFilePath);
        if(!dirFile.exists()){
            dirFile.mkdir();
        }
        File sourceFile = new File(tempFilePath+className+".java");
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

    public void parseJsp(String path){
        //path 类似 a/index.jsp
        init(path);
        if(!checkClassFile()){
            String sourceFilePath = createSourceFile(path);
            compiler(sourceFilePath);
        }
        excute();
    }

    private void init(String path){
        WebApplication webApplication = WebApplication.getInstance();
        tempFilePath = webApplication.getRoot()+"/jsp/";
        packageName = "com.prince.server.jsp.temp";
        className = path.replace("/","_").replace(".jsp","");
        classAllName = packageName+"."+className;
        classPath = this.getClass().getResource("/").getPath();
        classFilePath = classPath+packageName.replaceAll(".","/")+"/"+className+".class";
    }
    private boolean checkClassFile(){
        File f = new File(classFilePath);
        return f.exists();
    }

    public static void main(String[] args) {
        CreateJSPFile c = new CreateJSPFile();
        String filePath = c.createSourceFile();
        String className = c.compiler(filePath);
        c.excute(className);
    }
}
