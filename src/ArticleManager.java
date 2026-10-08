import java.util.ArrayList;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class ArticleManager {

    private ArrayList<Article> articles = new ArrayList<>();
    private int nextId = 1;

    public void showArticles() {

        if (articles.isEmpty()) {

            System.out.println(
                "No existen artículos"
            );

            return;
        }else{
            for (Article article : articles) {

            System.out.println("[" + article.getId() + "] " + article.getTitle());
            // System.out.println(article.getTitle());
            System.out.println(article.getContent());
            System.out.println("--------------");

            }
        }
    }

    public void addArticle(String title, String content){ 
        Article article = 
                new Article(
                    nextId, 
                    title,
                    content);

        articles.add(article);
        nextId++;
    }

    public Article findArticleById(int id){
        for (Article article : articles) {
            if (article.getId() == id) {
                return article;
            }
        }
        return null;
    }

    public void searchArticle(int id){
        // for(Article article : articles){
        //     if (article.getTitle().equalsIgnoreCase(title)) {
        //         System.out.println("**Articulo encontrado**");
        //         System.out.println(article.getTitle());
        //         System.out.println(article.getContent());

        //         return;
        // }
        Article article = findArticleById(id);
        if (article == null) {
            System.out.println("No se encontró el artículo");
            return;
        }
        //mostrar el artículo encontrado, id, título y contenido recliclando showArticles()
        System.out.println("**Articulo encontrado**");
        System.out.println("[" + article.getId() + "] " + article.getTitle());
        System.out.println(article.getContent());
        
    }

    public void deleteArticle(int id){
        for (int i = 0; i < articles.size(); i++) {
            if (articles.get(i).getId() == id) {
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
                    article.getId()
                    + "|"
                    +article.getTitle()
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

    public void loadArticles() {
        try {
            Scanner scanner = new Scanner(new File("articles.txt"));
            int maxId = 0;
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String[] parts = line.split("\\|");
                if (parts.length >= 3) {
                    int id = Integer.parseInt(parts[0]);
                    String title = parts[1];
                    String content = parts[2];

                    Article article = new Article(id, title, content);
                    articles.add(article);

                    if (id > maxId) {
                        maxId = id;
                    }
                }
            }
            nextId = maxId + 1;
            scanner.close();
        } catch (Exception e) {
            System.out.println("No se pudo cargar el articulo");
        }
    }
}
