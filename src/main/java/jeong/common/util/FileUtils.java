package jeong.common.util;

import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import jeong.board.BoardFileVo;

@Component("fileUtils")
public class FileUtils {
	
	private static final String filePath = "C:\\Users\\doanq\\Documents\\workspace-sts-3.9.7.RELEASE\\SpringJeongWeb\\src\\main\\webapp\\WEB-INF\\uploadFiles\\";

	private BoardFileVo bFileVo;
	private List<BoardFileVo> list;
	
	public List<BoardFileVo> parseInsertFileInfo(BoardFileVo fileVo, HttpServletRequest request) throws Exception {
		MultipartHttpServletRequest multipartHttpServletRequest = (MultipartHttpServletRequest)request;
		Iterator<String> iterator = multipartHttpServletRequest.getFileNames();
		
		MultipartFile multipartFile = null;
		String ori_file_name = null;
		String stored_file_name = null;
		String originalFileExtension = null;
	    
	    list = new ArrayList<>();
		
		File file = new File(filePath);
		
		if(file.exists() == false) {
			file.mkdirs();
		}
		
		while(iterator.hasNext()) {
			multipartFile = multipartHttpServletRequest.getFile(iterator.next());
			if(multipartFile.isEmpty()==false) {
				ori_file_name = multipartFile.getOriginalFilename();
				originalFileExtension = ori_file_name.substring(ori_file_name.lastIndexOf("."));
				stored_file_name = CommonUtils.getRandomString() + originalFileExtension;
                
                file = new File(filePath + stored_file_name);
                multipartFile.transferTo(file);
                
                bFileVo = new BoardFileVo();
                
                bFileVo.setBrdid(fileVo.getBrdid());
                bFileVo.setBrdidx(fileVo.getBrdidx());
                bFileVo.setOri_file_name(ori_file_name);
                bFileVo.setStored_file_name(stored_file_name);
                bFileVo.setFile_size(multipartFile.getSize());
                bFileVo.setReg_id(fileVo.getReg_id());
                bFileVo.setFileext(ori_file_name.substring(ori_file_name.lastIndexOf(".") + 1));
			
                list.add(bFileVo);
			}
		}
		
		return list;
	}
	
	public List<BoardFileVo> parseUpdateFileInfo(BoardFileVo fileVo, HttpServletRequest request) throws Exception{
        MultipartHttpServletRequest multipartHttpServletRequest = (MultipartHttpServletRequest)request;
        Iterator<String> iterator = multipartHttpServletRequest.getFileNames();

        MultipartFile multipartFile = null;
        String originalFileName = null;
        String originalFileExtension = null;
        String storedFileName = null;
	   	String requestName = null;
	   	String fileidx = null;
        list = new ArrayList<>();
//        int count = 0;

        while(iterator.hasNext()){
            multipartFile = multipartHttpServletRequest.getFile(iterator.next());
            if(multipartFile.isEmpty() == false){
                originalFileName = multipartFile.getOriginalFilename();
                originalFileExtension = originalFileName.substring(originalFileName.lastIndexOf("."));
                storedFileName = CommonUtils.getRandomString() + originalFileExtension;

                multipartFile.transferTo(new File(filePath + storedFileName));

                bFileVo = new BoardFileVo();

                bFileVo.setIs_new("Y");
                bFileVo.setBrdid(fileVo.getBrdid());
                bFileVo.setBrdidx(fileVo.getBrdidx());
                bFileVo.setOri_file_name(originalFileName);
                bFileVo.setStored_file_name(storedFileName);
                bFileVo.setFile_size(multipartFile.getSize());
                bFileVo.setReg_id(fileVo.getReg_id());
                bFileVo.setFileext(originalFileName.substring(originalFileName.lastIndexOf(".") + 1));

                list.add(bFileVo);
            }

            else{

                if(fileVo.getF_idx() != null){
                	requestName = multipartFile.getName();
                	fileidx = requestName.substring(requestName.indexOf("_")+1);


                	bFileVo = new BoardFileVo();
                	bFileVo.setDel_gb("N");
                	bFileVo.setBrdidx(fileVo.getBrdidx());
                	bFileVo.setF_idx(Integer.parseInt(fileidx));

                    list.add(bFileVo);
                }
            }
        }


        return list;
    }
}
