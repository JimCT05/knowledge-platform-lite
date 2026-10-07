import java.util.ArrayList;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class ArticleManager {

    private ArrayList<Article> articles = new ArrayList<>();

    // public static void showArticles(ArrayList<Article> articles) {
    //     for (Article article : articles) {
    //         System.out.println(article.getTitle());
    //         System.out.println(article.getContent());
    //     }
    // }

    public void showArticles() {

        for (Article article : articles) {

            System.out.println(article.getTitle());
            System.out.println(article.getContent());
            System.out.println("--------------");

        }

    }

    public void addArticle(Article article) {

        articles.add(article);
    }

    Article findArticleByTitle(String title){
        for (Article article : articles) {
            if (article.getTitle().equalsIgnoreCase(title)) {
                return article;
            }
        }
        return null;
    }

    public void searchArticle(String title){
        // for(Article article : articles){
        //     if (article.getTitle().equalsIgnoreCase(title)) {
        //         System.out.println("**Articulo encontrado**");
        //         System.out.println(article.getTitle());
        //         System.out.println(article.getContent());

        //         return;
        //     }
        // }
        Article article = findArticleByTitle(title);
        if (article == null) {
            System.out.println("No se encontró el artículo");
            return;
        }
        System.out.println(article.getTitle());
        System.out.println(article.getContent());
        
    }

    public void deleteArticle(String title){
        for (int i = 0; i < articles.size(); i++) {
            if (articles.get(i).getTitle().equals(title)) {
                articles.remove(i);

                System.out.println("Artículo eliminado");
                return;
            }
        }
        System.out.println("Artículo no encontrado");
    }

    public void modifyArticle(Article article,String newTitle, String newContent){
        article.setTitle(newTitle);
        article.setContent(newContent);
    }

    public void saveArticles(){
        try {
            FileWriter writer = new FileWriter("articles.txt");
            for (Article article : articles) {
                writer.write(
                    article.getTitle()
                    + "|"
                    + article.getContent()
                    + "\n"
                );
            }
            writer.close();
            
        } catch (IOException e) {
            System.out.println("Error al guardar");
        }

    }

    public void loadArticles(){
        try {
            Scanner scanner = new Scanner(new File("articles.txt"));
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String[] parts = line.split("\\|");
                String title = parts[0];
                String content = parts[1];

                Article article = new Article(title, content);
                addArticle(article);
            }
            scanner.close();
        } catch (Exception e) {
            System.out.println("No se pudo cargar el articulo");
        }
    }
}
