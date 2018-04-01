package com.loveluckystar.lucene.demo.crud;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.document.NumericField;
import org.apache.lucene.index.IndexReader;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;
import org.apache.lucene.util.Version;
import org.junit.Test;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Date;
import java.util.Random;

/**
 * Created by mxy on 2018/3/22.
 */

public class LuceneCRUDTest {

    @Test
    public void index(){
        IndexWriter writer = null;
        try {
            // 1、创建文件夹
            Directory directory = FSDirectory.open(new File("/Users/mxy/lucene/index"));

            // 2、创建IndexWriter
            // 2.1 标准分词器
            Analyzer analyzer = new StandardAnalyzer(Version.LUCENE_35);
            // 2.2 IndexWriter配置
            IndexWriterConfig indexWriterConfig = new IndexWriterConfig(Version.LUCENE_35, analyzer);
            // 2.3 赋值writer
            writer = new IndexWriter(directory,indexWriterConfig);
            // 2.4 查看/Users/mxy/lucene/document目录下所有文件
            File dFile = new File("/Users/mxy/lucene/document");
            File[] files = dFile.listFiles();
            // 2.5 遍历文本文件
            for (File file:files){
                // 3、创建Document文档记录
                Document document = new Document();
                // 4、写入field
                document.add(new Field("content",new FileReader(file)));
                document.add(new Field("filename",file.getName(), Field.Store.YES,Field.Index.ANALYZED));
                document.add(new Field("filepath",file.getAbsolutePath(), Field.Store.YES, Field.Index.NOT_ANALYZED_NO_NORMS));
                document.add(new NumericField("num", Field.Store.YES,true).setIntValue(new Random().nextInt(10)));
                document.add(new NumericField("date",Field.Store.YES,true).setLongValue(new Date().getTime()));
                // 5、通过IndexWriter添加文档到索引中
                writer.addDocument(document);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if(writer != null){
                    writer.close();
                }
            }catch (Exception e1){
                e1.printStackTrace();
            }
        }
    }

    @Test
    public void updateDoc(){
        IndexWriter writer = null;
        IndexReader reader = null;
        try {
            Directory directory = FSDirectory.open(new File("/Users/mxy/lucene/index"));
            Analyzer analyzer = new StandardAnalyzer(Version.LUCENE_35);
            IndexWriterConfig indexWriterConfig = new IndexWriterConfig(Version.LUCENE_35, analyzer);
            writer = new IndexWriter(directory, indexWriterConfig);

//            writer.updateDocument();
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            if(writer!=null){
                try {
                    writer.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    @Test
    public void deleteDoc(){
        IndexWriter writer = null;
        try {
            Directory directory = FSDirectory.open(new File("/Users/mxy/lucene/index"));
            Analyzer analyzer = new StandardAnalyzer(Version.LUCENE_35);
            IndexWriterConfig indexWriterConfig = new IndexWriterConfig(Version.LUCENE_35, analyzer);
            writer = new IndexWriter(directory,indexWriterConfig);

        }catch (Exception e){
            e.printStackTrace();
        }finally {
            if(writer!=null){
                try {
                    writer.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    @Test
    public void merge(){
        IndexWriter writer = null;
        try {
            Directory directory = FSDirectory.open(new File("/Users/mxy/lucene/index"));
            Analyzer analyzer = new StandardAnalyzer(Version.LUCENE_35);
            IndexWriterConfig indexWriterConfig = new IndexWriterConfig(Version.LUCENE_35, analyzer);
            writer = new IndexWriter(directory,indexWriterConfig);
            writer.forceMergeDeletes();
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            if(writer!=null){
                try {
                    writer.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    @Test
    public void test1(){
        System.out.println(new Random().nextInt(10));
    }
}
