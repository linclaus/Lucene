package com.seu.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.document.Field.Index;
import org.apache.lucene.document.Field.Store;

public class FileDocumentUtil {

	public static Document file2Document(String dataSourcePathString) {
		File file=new File(dataSourcePathString);
		Document doc =new Document();
		Field fileName=new Field("fileName",file.getName(), Store.YES,Index.TOKENIZED);
		Field content = new Field("content",getFileContent(file),Store.YES,Index.TOKENIZED);
		Field size=new Field("size",""+file.length(),Store.YES,Index.NO);
		Field path=new Field("path",dataSourcePathString,Store.YES,Index.UN_TOKENIZED);
		doc.add(fileName);
		doc.add(content);
		doc.add(size);
		doc.add(path);
		return doc;
	}
	private static String getFileContent(File file){
		String content="";
		String readContent="";

		try {
			BufferedReader br=new BufferedReader(new FileReader(file));
			while((content=br.readLine())!=null){
				readContent+=content;
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return readContent;
	}
//	public static void document2File(Document doc){
//		
//	}
	public static void printDocumentInfo(Document doc){
//		doc.getField(arg0);
	}

}
