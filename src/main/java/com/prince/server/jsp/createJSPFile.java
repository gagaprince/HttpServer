package com.prince.server.jsp;

import com.prince.server.http.WebApplication;

import javax.tools.JavaCompiler;
import javax.tools.ToolProvider;
import java.io.*;
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
        sourceSb.append("import com.prince.server.http.Request;").append(br);
        sourceSb.append("import com.prince.server.http.Response;").append(br);
        sourceSb.append("import java.io.IOException;").append(br);
        sourceSb.append("import java.io.OutputStream;").append(br);

        sourceSb.append("public class ").append(className).append("{").append(br);
        sourceSb.append("   public void out(Request request,Response response){").append(br);
        sourceSb.append("       OutputStream out = response.getOutputStream();").append(br);
        sourceSb.append("       try {").append(br);

        File jspFile = new File(jspFilePath);
        BufferedReader bfr = null ;
        String line = null;
        try {
            bfr = new BufferedReader(new InputStreamReader(new FileInputStream(jspFile),"utf-8"));
            while((line=bfr.readLine())!=null){
                if(!line.contains("<%")&&!line.contains("%>")){
                    sourceSb.append("           out.write(\"").append(line.replace("\"","\\\"")).append("\".getBytes());").append(br);
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        sourceSb.append("           out.flush();").append(br);
        sourceSb.append("           out.close();").append(br);
        sourceSb.append("       } catch (IOException e) {").append(br);
        sourceSb.append("           e.printStackTrace();").append(br);
        sourceSb.append("       }").append(br);
        sourceSb.append("   }").append(br);
        sourceSb.append("}").append(br);
        return sourceSb.toString();
    }

    public String createSourceFile(String path){
        String source = giveMeSource(path);
        System.out.println(source);

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
            System.out.println(sourceFilePath);
//            compiler(sourceFilePath);
        }
//        excute();
    }

    private void init(String path){
        WebApplication webApplication = WebApplication.getInstance();
        jspFilePath = webApplication.getWebRoot()+path;
        tempFilePath = System.getProperty("user.dir")+"/src/main/java/com/prince/server/jsp/temp/";
        packageName = "com.prince.server.jsp.temp";
        className = path.replace("/","_").replace(".jsp","");
        classAllName = packageName+"."+className;
        classPath = this.getClass().getResource("/").getPath();
        classFilePath = classPath+packageName.replaceAll("\\.","/")+"/"+className+".class";
    }
    private boolean checkClassFile(){
        System.out.println(classFilePath);
        File f = new File(classFilePath);
        return f.exists();
    }

}
