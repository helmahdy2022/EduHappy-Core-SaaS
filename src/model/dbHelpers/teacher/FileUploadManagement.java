package model.dbHelpers.teacher;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

import javax.servlet.ServletException;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.apache.tika.exception.TikaException;
import org.apache.tika.metadata.HttpHeaders;
import org.apache.tika.metadata.Metadata;
import org.apache.tika.metadata.TikaMetadataKeys;
import org.apache.tika.mime.MediaType;
import org.apache.tika.parser.AutoDetectParser;
import org.apache.tika.parser.ParseContext;
import org.apache.tika.parser.Parser;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;
import model.dbHelpers.DbConnector;

public class FileUploadManagement {
	private static int mapFile(String entity, String entityIdName, String subEntityIdName, int entity_id, int subEntity_id, String fileName, String randomFileName, String mimeType) {

		String query = null;
		if(subEntityIdName==null)
		{
			query = "INSERT INTO "+entity+"FileName (File_id, "+entityIdName+", Real_File_Name, Fake_File_Name, Mime_Type) VALUES (null, ?, ?, ?, ?)";	
		}
		else
		{
			query = "INSERT INTO "+entity+"FileName (File_id, "+entityIdName+","+subEntityIdName+", Real_File_Name, Fake_File_Name, Mime_Type) VALUES (null, ?, ?, ?, ?,?)";
		}

		int updateQuery = 0;
		try (Connection connection = DbConnector.connectToDb();
				PreparedStatement pstatement = connection.prepareStatement(query)){
				if(subEntityIdName==null)
				{ 
					pstatement.setInt(1, entity_id);
					pstatement.setString(2, fileName);
					pstatement.setString(3, randomFileName);
					pstatement.setString(4, mimeType);
				}
				else
				{
					pstatement.setInt(1, entity_id);
					pstatement.setInt(2, subEntity_id);
					pstatement.setString(3, fileName);
					pstatement.setString(4, randomFileName);
					pstatement.setString(5, mimeType);
				}

				updateQuery = pstatement.executeUpdate(); 	
		} 
		catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return updateQuery;
	}
	
	private static int updateMappedFile(int file_id, String entity, String entityIdName, String subEntityIdName, int entity_id, int subEntity_id, String realFileName , String randomFileName, String mimeType) {
		String query = null;
		if(entity.equals("Students") || entity.equals("Parents") ||entity.equals("Teachers") || entity.equals("Admins"))
		{
			if(mimeType.startsWith("image"))
			query = "update "+entity+" set Real_File_Name=?, Fake_File_Name=?, Mime_Type=? where "+entityIdName+"="+entity_id;	
		}
		else
		{
			if(subEntityIdName==null)
			{
				query = "update "+entity+"FileName set Real_File_Name=?, Fake_File_Name=?, Mime_Type=? where "+entityIdName+"="+entity_id+" and File_id='"+file_id+"'";	
			}
			else
			{
				query = "update "+entity+"FileName set Real_File_Name=?, Fake_File_Name=?, Mime_Type=? where "+entityIdName+"="+entity_id+" and "+subEntityIdName+"="+subEntity_id+" and File_id='"+file_id+"'";	
			}
			
		}
		
		int updateQuery = 0;
		try (Connection connection = DbConnector.connectToDb();
				PreparedStatement pstatement = connection.prepareStatement(query)){
			pstatement.setString(1, realFileName);	
			pstatement.setString(2, randomFileName);
				pstatement.setString(3, mimeType);
				updateQuery = pstatement.executeUpdate();
   	
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return updateQuery;
	}
	
	public static int deleteMappedFile(int file_id, String entity, String entityIdName) {
		String query = "delete from "+entity+"FileName where File_id="+file_id;	
		if(entity.equals("Students") || entity.equals("Parents") || entity.equals("Teachers") || entity.equals("Admins"))
		{
			query = "update "+entity+" set Real_File_Name=null, Fake_File_Name=null, Mime_Type=null where "+entityIdName+"="+file_id;		
		}
		else
		{
			query = "delete from "+entity+"FileName where File_id="+file_id;	
		}
		int updateQuery = 0;
		try (Connection connection = DbConnector.connectToDb();
				PreparedStatement pstatement = connection.prepareStatement(query)){
				updateQuery = pstatement.executeUpdate();
   	
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return updateQuery;
	}

	public static String getFakeFileName(int file_id, String entity, String entityIdName) {
		String sFakeFileName = null;
		if(entity.equals("Students") || entity.equals("Parents") || entity.equals("Teachers") || entity.equals("Admins"))
		{
			sFakeFileName= "Select Fake_File_Name from "+entity+" where "+entityIdName+"="+file_id;
		}
		else
		{
			sFakeFileName= "Select Fake_File_Name from "+entity+"FileName where File_id="+file_id;
		}
		
		String existingFakeFileName=null;
		try (Connection connection = DbConnector.connectToDb();
				Statement statementFakeFileName = connection.createStatement();){
			ResultSet resultFakeFileName = statementFakeFileName.executeQuery(sFakeFileName);
			while(resultFakeFileName.next())
			{
				existingFakeFileName = resultFakeFileName.getString("Fake_File_Name");
			}
			

		}catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return existingFakeFileName;
	}

	public static String getRealFileName(String entity, String entityIdName, int entity_id, String subEntityIdName, int subEntity_id, String fileName) {
		String sRealFileName = null;
		if(entity.equals("Students") || entity.equals("Parents") || entity.equals("Teachers") || entity.equals("Admins"))
		{
			sRealFileName = "Select Real_File_Name from "+entity+" where "+entityIdName+"="+entity_id+" and Fake_File_Name='"+fileName+"'";
		}
		else if (subEntity_id!=-1)
		{
			sRealFileName = "Select Real_File_Name from "+entity+"FileName where "+entityIdName+"="+entity_id+" and "+subEntityIdName+"="+subEntity_id+" and Fake_File_Name='"+fileName+"'";
		}
		else
		{
			sRealFileName = "Select Real_File_Name from "+entity+"FileName where "+entityIdName+"="+entity_id+" and Fake_File_Name='"+fileName+"'";
		}
		
		String realFileName=null;
		try (Connection connection = DbConnector.connectToDb();
				Statement statementRealFileName = connection.createStatement();){
			ResultSet resultRealFileName = statementRealFileName.executeQuery(sRealFileName);
			while(resultRealFileName.next())
			{
				realFileName = resultRealFileName.getString("Real_File_Name");
			}
			

		}catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return realFileName;
	}	
	
	public static int getFile_id(String entity, String entityIdName, int entity_id, String subEntityIdName, int subEntity_id, String fileName) {
		int file_id=0;
		String sFile_Id = null;
		if(entity.equals("Students") || entity.equals("Parents") || entity.equals("Teachers") || entity.equals("Admins"))
		{
			file_id = entity_id;
		}
		else if (subEntity_id!=-1)
		{
			sFile_Id = "Select file_id from "+entity+"FileName where "+entityIdName+"="+entity_id+" and "+subEntityIdName+"="+subEntity_id+" and Fake_File_Name='"+fileName+"'";
		}
		else
		{
			sFile_Id = "Select file_id from "+entity+"FileName where "+entityIdName+"="+entity_id+" and Fake_File_Name='"+fileName+"'";
		}
		try (Connection connection = DbConnector.connectToDb();
				Statement statementFile_Id = connection.createStatement();){
			ResultSet resultFile_Id = statementFile_Id.executeQuery(sFile_Id);
			while(resultFile_Id.next())
			{
				file_id = resultFile_Id.getInt("File_id");
			}
			

		}catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return file_id;
	}
	
	public static String getFile_Category(String fileExt) {
	
		String sFileCategory = "Select File_Category from mimewhitelist where file_extension='."+fileExt+"'";
		String file_Category=null;
		try (Connection connection = DbConnector.connectToDb();
				Statement statementFileCategory = connection.createStatement();){
			ResultSet resultFileCategory = statementFileCategory.executeQuery(sFileCategory);
			while(resultFileCategory.next())
			{
				file_Category = resultFileCategory.getString("File_Category");
			}
		}catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return file_Category;
	}
	
	public static ArrayList<String> fileUpload(String appPath, int account_id, int school_id, String entity, String subEntity, String entityIdName, String subEntityIdName, int entity_id, int subEntity_id, List<FileItem> items, long totalFilesSizeParam, long fileSizeParam, int fileQtyParam) throws IllegalStateException, FileNotFoundException, IOException, ServletException, FileUploadException
	{
   		ArrayList<String> uploadMessage = new ArrayList<String>();
   		
   		// creates the save directory for account_id if it does not exists		
   		String savePathAccountID = appPath + "uploadFiles/Accounts/"+account_id;
   		File accountSaveDir = new File(savePathAccountID);
		if (!accountSaveDir.exists()) {
			accountSaveDir.mkdir();
		}

		String sSchool = "/";
			
		if(school_id!=-1)
		{
			// creates the save directory for schools if it does not exists
			String savePathSchools = appPath + "uploadFiles/Accounts/"+account_id+"/Schools/";
			File schoolsSaveDir = new File(savePathSchools);
			if (!schoolsSaveDir.exists()) {
				schoolsSaveDir.mkdir();
			}						
			// creates the save directory for school_id if it does not exists
			String savePathSchoolID = appPath + "uploadFiles/Accounts/"+account_id+"/Schools/"+school_id;
			File schoolIDSaveDir = new File(savePathSchoolID);
			if (!schoolIDSaveDir.exists()) {
				schoolIDSaveDir.mkdir();
			}
			sSchool = "/Schools/"+school_id+"/";
		}
		
	
		// creates the save directory for Entity if it does not exists
		String savePathEntity = appPath + "uploadFiles/Accounts/"+account_id+sSchool+entity+"/";  	
		File entitySaveDir = new File(savePathEntity);
		if (!entitySaveDir.exists()) {
			entitySaveDir.mkdir();
		}
		
		// creates the save directory for Sub Entity if it does not exists
		if(subEntity_id==-1)
		{
			String savePathSubEntity = appPath + "uploadFiles/Accounts/"+account_id+sSchool+entity+"/"+subEntity +"/";  	
			File subEntitySaveDir = new File(savePathSubEntity);
			if (subEntity!=null && !subEntitySaveDir.exists()) {
				subEntitySaveDir.mkdir();
			}
		}

		
		// creates the save directory for Entity_id if it does not exists
		String savePath=null;
		if(subEntity!=null)
		{
			if(subEntity_id==-1)
			{
				savePath = appPath + "uploadFiles/Accounts/"+account_id+sSchool+entity+"/"+subEntity +"/"+entity_id; 
				File fileSaveDir = new File(savePath);
				if (!fileSaveDir.exists()) 
				{
				  fileSaveDir.mkdir();
				}	
			}			 
		}				
		else
		{
			savePath = appPath + "uploadFiles/Accounts/"+account_id+sSchool+entity+"/"+entity_id; 
			File fileSaveDir = new File(savePath);
			if (!fileSaveDir.exists()) 
			{
			  fileSaveDir.mkdir();
			}	
		}			 	
	
		// creates the save directory for SubEntity_id if it does not exists
		//String savePathSubEntityID=null;
		if(subEntity_id!=-1)
		{
			String entityIdDirPath = appPath + "uploadFiles/Accounts/"+account_id+sSchool+entity+"/"+entity_id;
			File entityIdDir = new File(entityIdDirPath);
			if (!entityIdDir.exists()) {
				entityIdDir.mkdir();
			}	
			
			String subEntityDirPath = appPath + "uploadFiles/Accounts/"+account_id+sSchool+entity+"/"+entity_id +"/"+subEntity;
			File subEntityDir = new File(subEntityDirPath);
			if (!subEntityDir.exists()) {
				subEntityDir.mkdir();
			}
			
			savePath = appPath + "uploadFiles/Accounts/"+account_id+sSchool+entity+"/"+entity_id +"/"+subEntity+"/"+subEntity_id;  
			File subEntityIDSaveDir = new File(savePath);
			if (!subEntityIDSaveDir.exists()) {
				subEntityIDSaveDir.mkdir();
			}	
		}							 	
		////////////////////////////Apache Commons ///////////////////////////
		int fileQty = 0;
		long totalFilesSize = 0 ;
		for (FileItem item : items) 
        {
			if(!item.isFormField())
			{
				totalFilesSize = totalFilesSize + item.getSize();
				fileQty++;
			}
        }
		if(fileQty > fileQtyParam)
		{
			uploadMessage.add("Maximum total number of files allowed per upload is "+fileQtyParam+" Files. Upload aborted");
			return uploadMessage;
		}
		if(totalFilesSize > totalFilesSizeParam)
		{
    		uploadMessage.add("You are uploading a file or group of files that exceeded the total limit. All files were not uploaded");
    		return uploadMessage;
		}
		for (FileItem item : items) 
        {
			boolean rejectUpload = false;
        	long fileSize = item.getSize();
        	if(fileSize > fileSizeParam)
        	{
        		uploadMessage.add("File ("+FilenameUtils.getName(item.getName())+") exceeded the limits. File was not uploaded");
        		rejectUpload = true;
        		//return uploadMessage;
        	}
        	int file_id= 0;
        	if(item.isFormField())
            {
                String fieldName = item.getFieldName();
                String fieldValue = item.getString();
                if(fieldName.equals("File_id") && fieldValue!=null)
                {
                	file_id = Integer.parseInt(fieldValue);
                }               	
            }
            else
            {
                // Process form file field (input type="file").
               String fileName = FilenameUtils.getName(item.getName());
               InputStream fileContent = item.getInputStream();
			
               ////////////////////////////////////////////////////////////////////////
			  if (!fileName.equals("") && rejectUpload==false)
			  {
				  
				  File file = new File(savePath + "/" + fileName);
				  Files.copy(fileContent, file.toPath(), StandardCopyOption.REPLACE_EXISTING);			  
		   		/////////////////////////// First check the file mime-type ///////////////////
	 	        AutoDetectParser parser = new AutoDetectParser();
	 	        parser.setParsers(new HashMap<MediaType, Parser>());
	 	        Metadata metadata = new Metadata();
	 	        metadata.add(TikaMetadataKeys.RESOURCE_NAME_KEY, file.getName());
	 	        InputStream stream = new FileInputStream(file);
	 	        try {
						parser.parse(stream, new DefaultHandler(), metadata, new ParseContext());
					} catch (SAXException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (TikaException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
	 	        stream.close();

	 	        String mimeType = metadata.get(HttpHeaders.CONTENT_TYPE);
	 	        Boolean mimeTypeExist  = matchMimeType(mimeType);
	 	        
	 	        if(mimeTypeExist == false)
	 	        {
	 	        	
	 	        	file.delete();
	 	        	uploadMessage.add(fileName.replace('&', '/')+": File not allowed - Upload Failed "+mimeType); 	        	      	        	
	 	        }
	 	        else
	 	        {
	 	      		int updateQueryNew = 0;
	 	      		int updateQueryModify = 0;
	 	        	String randomFileName = UUID.randomUUID().toString()+getFileExtension(mimeType);
	 	        	File renamedFile = new File(savePath + "/" + randomFileName);
	 	        	file.renameTo(renamedFile);
	 	        	////////////////////////////////////////////Online Quiz ////////////////////////////////////////
		    	  if(entity.equals("Assignments")&&(mimeType.equals("application/zip")||mimeType.equals("application/x-compressed")||mimeType.equals("application/x-zip-compressed")||mimeType.equals("multipart/x-zip")))
			      {
					  /////////////////////////////unpack zip file if it's a zip file /////////////////
					  File quizDir = new File(savePath+"/OnlineQuiz");
					  FileUtils.deleteQuietly(quizDir);
					  quizDir.mkdir();
				      String destination = savePath+"/OnlineQuiz";
				      //String password = "password";
	
				      //try {
/*				           ZipFile zipFile = new ZipFile(savePath + "/" + randomFileName);
				           if (zipFile.isEncrypted()) {
				              zipFile.setPassword(password);
				           }
				           zipFile.extractAll(destination);*/
				          ////////////////////////////////////////////////
				      	   uploadMessage = unzip(savePath + "/" + randomFileName, destination, uploadMessage);
				      	   if((uploadMessage.size()!=0 && (uploadMessage.get(0).contains("Too many") || uploadMessage.get(0).contains("too big"))))
				      	   {
					      		 FileUtils.deleteQuietly(quizDir);
					      		 renamedFile.delete();  
					      		 break;
				      	   }
				    	  ///////////check if it's scorm by searching imsmanifest.xml file in the directory/////////////////////////
				           File[] files = quizDir.listFiles();
				           boolean fileIsScorm = false;
				           for(File qfile : files)
				           {
				        	   if(qfile.getName().equals("imsmanifest.xml"))
				        	   {
				        		   new File(savePath + "/" + randomFileName).delete();
				        		   //zipFile.getFile().delete();					        		   
					 	        	String existingFakeFileName = null;  
					 	        	if(file_id==0)
					 	        	{
					 	        		file_id = checkExistingQuiz(entity, entityIdName, entity_id);
					 	        	}
					 	        	if(file_id!=0)
					 	        	{
					 	        		existingFakeFileName = "OnlineQuiz";
					 	        	}
					 	        	
				        		   if(existingFakeFileName==null)
				        		   {
				        			   updateQueryNew = mapFile(entity, entityIdName, subEntityIdName, entity_id, subEntity_id, fileName, "OnlineQuiz", mimeType);
					   	        		if(updateQueryNew!=0)
					     	        	{
					     	        		uploadMessage.add(fileName.replace('&', '/')+": File uploaded successfully"); 
					     	        		file_id = getFile_id(entity, entityIdName, entity_id, subEntityIdName, subEntity_id, randomFileName);
					     	        	}
					     	        	else
					     	        	{
					     	        		renamedFile.delete(); 
					     	        		uploadMessage.add(fileName.replace('&', '/')+": Failed to upload the file, please try again"); 
					     	        	}
				        		   }
				        		   else
				        		   {
				        			   updateQueryModify = updateMappedFile(file_id, "Assignments", "Assignment_id", null, entity_id, -1, fileName, "OnlineQuiz", mimeType) ;
					   			    	if(updateQueryModify!=0)
										{
											File oldFile = new File(savePath + "/" + existingFakeFileName);
											oldFile.delete();
											uploadMessage.add(fileName.replace('&', '/')+": File replaced successfully"); 
										}
										else
										{
											renamedFile.delete(); 
											uploadMessage.add(fileName.replace('&', '/')+": Failed to replace the file, please try again"); 
										}
				        		   }					        		   
				        		   fileIsScorm = true; 
				        		   break;
				        	   }
				        	   
				           }
			        	   if(fileIsScorm == false)
			        	   {
					           /////////if not scorm then delete the directory/////////////////
				        	   FileUtils.deleteQuietly(quizDir);
				    		  	
				 	        	String existingFakeFileName = null;  
				 	        	if(file_id==0)
				 	        	{
				 	        		file_id = checkExistingRealName(entity, entityIdName, entity_id, fileName);
				 	        	}
				 	        	if(file_id!=0)
				 	        	{
				 	        		existingFakeFileName = getFakeFileName(file_id, entity, entityIdName);
				 	        	}
				 	        	
				    		  if(existingFakeFileName!=null && !existingFakeFileName.equals("OnlineQuiz"))
				 	        	{
				 	        		updateQueryModify = updateMappedFile(file_id, entity, entityIdName, subEntityIdName, entity_id, subEntity_id, fileName, randomFileName, mimeType);
							    	if(updateQueryModify!=0)
									{
										File oldFile = new File(savePath + "/" + existingFakeFileName);
										oldFile.delete();
										uploadMessage.add(fileName.replace('&', '/')+": File replaced successfully"); 
									}
									else
									{
										renamedFile.delete(); 
										uploadMessage.add(fileName.replace('&', '/')+": Failed to replace the file, please try again"); 
									}
				 	        		
				 	        	}
				 	        	else
				 	        	{
				 	        		updateQueryNew = mapFile(entity, entityIdName, subEntityIdName, entity_id, subEntity_id, fileName, randomFileName, mimeType);
					        		if(updateQueryNew!=0)
				     	        	{
				     	        		uploadMessage.add(fileName.replace('&', '/')+": File uploaded successfully"); 
				     	        		file_id = getFile_id(entity, entityIdName, entity_id, subEntityIdName, subEntity_id, randomFileName);
				     	        	}
				     	        	else
				     	        	{
				     	        		renamedFile.delete(); 
				     	        		uploadMessage.add(fileName.replace('&', '/')+": Failed to upload the file, please try again"); 
				     	        	}			
				 	        	}	
			        	   }					        	 					        	   
			      }	 	        	
 	        	//////////////////////////////////////////////////////////////////////////////////////////////////
		    	  else
		    	  {
		    		  	
		 	        	String existingFakeFileName = null;  
		 	        	if(file_id==0)
		 	        	{
		 	        		file_id = checkExistingRealName(entity, entityIdName, entity_id, fileName);
		 	        	}
		 	        	if(file_id!=0)
		 	        	{
		 	        		existingFakeFileName = getFakeFileName(file_id, entity, entityIdName);
		 	        	}		 	        	
		    		  if((existingFakeFileName!=null && !existingFakeFileName.equals("OnlineQuiz")) || entity.equals("Students") || entity.equals("Parents") || entity.equals("Teachers")|| entity.equals("Admins"))
		 	        	{
		 	        		updateQueryModify = updateMappedFile(file_id, entity, entityIdName, subEntityIdName, entity_id, subEntity_id, fileName, randomFileName, mimeType);
					    	if(updateQueryModify!=0)
							{
								File oldFile = new File(savePath + "/" + existingFakeFileName);
								oldFile.delete();
								if(entity.equals("Students") || entity.equals("Parents") || entity.equals("Teachers") || entity.equals("Admins"))
								{

									uploadMessage.add("Photo uploaded successfully"); 			
								}
								else
								{
									uploadMessage.add(fileName.replace('&', '/')+": File replaced successfully"); 
								}
								
							}
							else
							{
								renamedFile.delete(); 
								uploadMessage.add(fileName.replace('&', '/')+": Failed to replace the file, please try again"); 
							}
		 	        		
		 	        	}
		 	        	else
		 	        	{
		 	        		updateQueryNew = mapFile(entity, entityIdName, subEntityIdName, entity_id, subEntity_id, fileName, randomFileName, mimeType);
			        		if(updateQueryNew!=0)
		     	        	{
		     	        		uploadMessage.add(fileName.replace('&', '/')+": File uploaded successfully"); 
		     	        		file_id = getFile_id(entity, entityIdName, entity_id, subEntityIdName, subEntity_id, randomFileName);
		     	        	}
		     	        	else
		     	        	{
		     	        		renamedFile.delete(); 
		     	        		uploadMessage.add(fileName.replace('&', '/')+": Failed to upload the file, please try again"); 
		     	        	}			
		 	        	}
		    	  }	 
	 	        }	
			  }           	
            }		  
		}
		return uploadMessage;
	}
////////////////////////Unzip using ZipInputStream///////////////////////////////////////////////////////////
	static final int BUFFER = 512;
	static final int TOOBIG = 0x1024000; //0x6400000; // Max size of unzipped data, 100MB
	static final int TOOMANY = 1024;     // Max number of files
	public final static ArrayList<String> unzip(String filename, String targetDir, ArrayList<String> uploadMessage) throws java.io.IOException {
	
	FileInputStream fis = new FileInputStream(filename);
	ZipInputStream zis = new ZipInputStream(new BufferedInputStream(fis));
	
	ZipEntry entry;
	int entries = 0;
	long total = 0;
	try 
	{
		while ((entry = zis.getNextEntry()) != null) 
		{
			int count;
			byte data[] = new byte[BUFFER];
			// Write the files to the disk, but ensure that the filename is valid,
			// and that the file is not insanely big
			String name = targetDir + "/" + entry.getName()+"/";
			if (entry.isDirectory()) 
			{
				new File(name).mkdir();		    	  
				continue;
			}
			FileOutputStream fos = new FileOutputStream(name);
			BufferedOutputStream dest = new BufferedOutputStream(fos, BUFFER);
			while (total + BUFFER <= TOOBIG && (count = zis.read(data, 0, BUFFER)) != -1) {
			dest.write(data, 0, count);
			total += count;
			}
			dest.flush();
			dest.close();
			zis.closeEntry();
			entries++;
			if (entries > TOOMANY) 
			{
				uploadMessage.add("Too many files to unzip.");
				throw new IllegalStateException();
			}
			if (total > TOOBIG) 
			{
				uploadMessage.add("File being unzipped is too big.");
				throw new IllegalStateException();			
			}
		}
		return uploadMessage;
	} 
	catch (IllegalStateException e)
	{		
		return uploadMessage;
	}
	finally 
	{
	zis.close();
	}
}
/////////////////////////////////////////////////////////////////////////////////////////////////////////////
	private static Boolean  matchMimeType(String mimeType) {
		String sMimeType = "Select mime_type from mimewhitelist where mime_type='"+mimeType+"'";
		Boolean mimeTypeExist = false;
		try (Connection connection = DbConnector.connectToDb();
				Statement statementMimeType = connection.createStatement();){
			ResultSet resultMimeType = statementMimeType.executeQuery(sMimeType);
			while(resultMimeType.next())
			{
				mimeTypeExist = true;
			}
			

		}catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return mimeTypeExist;
	}
	private static int  checkExistingRealName(String entity, String entityIdName, int entity_id, String realFileName) {
		String sRealFileName =null;
		if(entity.equals("Students") || entity.equals("Parents") || entity.equals("Teachers") || entity.equals("Admins"))
		{
			sRealFileName= "Select * from "+entity+" where "+entityIdName+"="+entity_id;
		}
		else
		{
			sRealFileName= "Select * from "+entity+"FileName where "+entityIdName+"="+entity_id;
		}

		int file_id = 0;
		try (Connection connection = DbConnector.connectToDb();
				Statement statementRealFileName = connection.createStatement();){
			ResultSet resultRealFileName = statementRealFileName.executeQuery(sRealFileName);
			while(resultRealFileName.next())
			{
				if(entity.equals("Students") || entity.equals("Parents") || entity.equals("Teachers") || entity.equals("Admins"))
				{
					file_id = entity_id;
					break;
				}
				else
				{
					if(realFileName.equals(resultRealFileName.getString("Real_File_Name")))
					{
						file_id = resultRealFileName.getInt("File_id");
						break;
					}
						
				}
			}
			

		}catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return file_id;
	}
	private static int  checkExistingQuiz(String entity, String entityIdName, int entity_id) {
		String sOnlineQuiz = "Select * from "+entity+"FileName where "+entityIdName+"="+entity_id;

		int file_id = 0;
		try (Connection connection = DbConnector.connectToDb();
				Statement statementOnlineQuiz = connection.createStatement();){
			ResultSet resultOnlineQuiz = statementOnlineQuiz.executeQuery(sOnlineQuiz);
			while(resultOnlineQuiz.next())
			{
				if(resultOnlineQuiz.getString("Fake_File_Name").equals("OnlineQuiz"))
				{
					file_id = resultOnlineQuiz.getInt("File_id");
					break;
				}
			}
			

		}catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return file_id;
	}
	private static String  getFileExtension(String mimeType) {
		String sFileExt = "Select file_Extension from mimewhitelist where mime_type='"+mimeType+"'";
		String file_Extension = null;
		try (Connection connection = DbConnector.connectToDb();
				Statement statementFileExt = connection.createStatement();){
			ResultSet resultFileExt = statementFileExt.executeQuery(sFileExt);
			while(resultFileExt.next())
			{
				file_Extension = resultFileExt.getString("File_Extension");
			}
			

		}catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return file_Extension;
	}
}
