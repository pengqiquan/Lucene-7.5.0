package lucene.rangeQuery;

import io.FileOperation;
import lucene.index.IndexFileWithManyFieldValues;
import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.core.WhitespaceAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.document.TextField;
import org.apache.lucene.index.*;
import org.apache.lucene.search.*;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.MMapDirectory;
import org.apache.lucene.util.BytesRef;

import java.io.IOException;
import java.nio.file.Paths;

/**
 * @author Lu Xugang
 * @date 2019-04-15 19:58
 */
public class TermRangeQueryTest {

    private Directory directory;

    {
        try {
            FileOperation.deleteFile("./data");
            directory = new MMapDirectory(Paths.get("./data"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private Analyzer analyzer = new WhitespaceAnalyzer();
    private IndexWriterConfig conf = new IndexWriterConfig(analyzer);
    private IndexWriter indexWriter;

    public void doSearch() throws Exception {
        conf.setUseCompoundFile(false);
        indexWriter = new IndexWriter(directory, conf);
//
//        Document doc ;
//        // 0
//        doc = new Document();
//        doc.add(new TextField("content", "a", Field.Store.YES));
//        doc.add(new TextField("name", "Cris", Field.Store.YES));
//        indexWriter.addDocument(doc);
//        // 1
//        doc = new Document();
//        doc.add(new TextField("content", "bcd", Field.Store.YES));
//        doc.add(new TextField("name", "Andy", Field.Store.YES));
//        indexWriter.addDocument(doc);
//        // 2
//        doc = new Document();
//        doc.add(new TextField("content", "ga", Field.Store.YES));
//        doc.add(new TextField("name", "Jack", Field.Store.YES));
//        indexWriter.addDocument(doc);
//
//        String values = "fififv fnfufkfkfl ftfufs fbfqfxfe fufmfefkfp fsfdfp fdfsfbfmfm fpfofqfy fwfvfffr flfcftfg fmfyfefv fsfifbflff flfefx fffifmfmfk";
//        // 3
//        doc = new Document();
//        doc.add(new TextField("content", values, Field.Store.YES));
//        doc.add(new TextField("name", "Jack", Field.Store.YES));
//        indexWriter.addDocument(doc);
//
//        // 4
//        doc = new Document();
//        doc.add(new TextField("content", "gc", Field.Store.YES));
//        doc.add(new TextField("name", "Pony", Field.Store.YES));
//        indexWriter.addDocument(doc);
//        // 5
//        doc = new Document();
//        doc.add(new TextField("content", "gch", Field.Store.YES));
//        doc.add(new TextField("name", "Jolin", Field.Store.YES));
//        indexWriter.addDocument(doc);
//        // 6
//        doc = new Document();
//        doc.add(new TextField("content", "gchb", Field.Store.YES));
//        doc.add(new TextField("name", "Jay", Field.Store.YES));
//        indexWriter.addDocument(doc);
//        indexWriter.commit();

        Document doc ;
        // 0
        doc = new Document();
        doc.add(new TextField("content", "a", Field.Store.YES));
        indexWriter.addDocument(doc);
        // 1
        doc = new Document();
        doc.add(new TextField("content", "b", Field.Store.YES));
        indexWriter.addDocument(doc);
        // 2
        doc = new Document();
        doc.add(new TextField("content", "c", Field.Store.YES));
        indexWriter.addDocument(doc);
        // 3
        doc = new Document();
        doc.add(new TextField("content", "a c e", Field.Store.YES));
        indexWriter.addDocument(doc);
        // 4
        doc = new Document();
        doc.add(new TextField("content", "h", Field.Store.YES));
        indexWriter.addDocument(doc);
        // 5
        doc = new Document();
        doc.add(new TextField("content", "c e", Field.Store.YES));
        indexWriter.addDocument(doc);
        // 6
        doc = new Document();
        doc.add(new TextField("content", "c a", Field.Store.YES));
        indexWriter.addDocument(doc);
        // 7
        doc = new Document();
        doc.add(new TextField("content", "f", Field.Store.YES));
        indexWriter.addDocument(doc);
        // 8
        doc = new Document();
        doc.add(new TextField("content", "b c d e c e", Field.Store.YES));
        indexWriter.addDocument(doc);
        // 9
        doc = new Document();
        doc.add(new TextField("content", "a c e a b c", Field.Store.YES));
        indexWriter.addDocument(doc);
        indexWriter.commit();


        IndexReader reader = DirectoryReader.open(indexWriter);
        IndexSearcher searcher = new IndexSearcher(reader);

        Query query = new TermRangeQuery("content", new BytesRef("bc"), new BytesRef("h"), true, true);
//        Query query = new TermRangeQuery("content", new BytesRef("c"), new BytesRef("f"), true, true);


        ScoreDoc[]docs = searcher.search(query, 3).scoreDocs;

        System.out.println("hah");
    }

    public static void main(String[] args)throws Exception {
        TermRangeQueryTest test = new TermRangeQueryTest();
        test.doSearch();
    }
}
