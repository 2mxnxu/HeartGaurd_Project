package HeartGuard.Util;

import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URLEncoder;
import java.util.Arrays;
import java.util.UUID;

@Component
public class FileUtil {
    String baseDir = System.getProperty("user.dir");
    String uploadPath = baseDir+"/build/resources/main/static/upload/";


    //업로드
    public String fileUpload(MultipartFile multipartFile){
        String uuid = UUID.randomUUID().toString();
        String fileName = uuid +"_"+multipartFile.getOriginalFilename().replaceAll("_","-");
        String filePath = uploadPath + fileName;
        File file2 = new File(uploadPath); if(!file2.exists()){file2.mkdir();}
        File file = new File(filePath);
        try {
            multipartFile.transferTo(file);
        }catch (IOException e){
            System.out.println(e); return null;
        }
        return fileName;

    }
    //업로드된 파일 다운로드
    public void fileDown(String filename, HttpServletResponse response){
        String downloadPath = uploadPath + filename;
        System.out.println("downloadPath = " + downloadPath);

        File file = new File(downloadPath);
        if(file.exists() == false){return ;}
        try{
            FileInputStream fin = new FileInputStream(downloadPath);
            long fileSize = file.length();
            System.out.println("fileSize = "+fileSize);
            byte[] bytes = new byte[(int)fileSize];
            fin.read(bytes); System.out.println(Arrays.toString( bytes));
            fin.close();
            String oldFilename = URLEncoder.encode(filename.split("_")[1],"UTF-8");
            response.setHeader( "Content-Disposition" , "attachment;filename="+oldFilename );

            ServletOutputStream fout = response.getOutputStream();
            fout.write( bytes );
            fout.close();

        }catch ( Exception e ){  System.out.println( e );}
    }

    public boolean fileDelete(String filename){
        String filePath = uploadPath + filename;
        File file = new File(filePath);
        if(file.exists()){
            file.delete();
            return true;
        }
        return false;
    }



}
