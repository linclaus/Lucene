package com.seu.helloworld;

import java.io.IOException;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.Term;
import org.apache.lucene.search.Hits;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.MultiPhraseQuery;

import com.seu.util.FileDocumentUtil;

public class HelloWorldTest {
	private static String dataSourcePathString="V:\\Java\\Eclipse+JDK\\eclipse\\Workspace\\Lucene\\LuceneDataSource\\BUILD.txt";
	private static String indexFile="V:\\Java\\Eclipse+JDK\\eclipse\\Workspace\\Lucene\\indexFile";
	
	static Analyzer analyzer=new StandardAnalyzer();
	
	public static void createIndex(){
		try {
			IndexWriter indexWriter=new IndexWriter(indexFile, analyzer, false);
			Document doc=FileDocumentUtil.file2Document(dataSourcePathString);
			indexWriter.addDocument(doc);
			indexWriter.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static void queryIndex(){
		try {
			IndexSearcher indexSearcher=new IndexSearcher(indexFile);
			Term term=new Term("content","lucene");
			MultiPhraseQuery multiPhraseQuery=new MultiPhraseQuery();
			multiPhraseQuery.add(term);
			Hits search = indexSearcher.search(multiPhraseQuery);
			for(int i=0;i<search.length();i++){
				System.out.println(search.doc(i).getField("path").stringValue());
//				System.out.println(search.doc(i).getField("content").stringValue());
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
//		createIndex();
		queryIndex();
	}
}
