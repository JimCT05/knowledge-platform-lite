import java.util.Scanner;

public class Main {
    public static void main(String[] args){

        ArticleManager manager = new ArticleManager();
        manager.loadArticles();

        // Article article1 = new Article("No arranca mi maquina virtual", "Paso 1: Reinciar MV");
        // Article article2 = new Article("Fallo al entrar a teletrabajo", "Paso 1: Revisa la conexión a internet\nPaso 2: Revisa conexión a VPN");
        // manager.addArticle(article1);
        // manager.addArticle(article2);

        // manager.showArticles();

        Scanner scanner = new Scanner(System.in);
        boolean running = true;
        while (running) {
            System.out.println("\nKnowledge Platform Lite");
            System.out.println("1 - Crear artículo");
            System.out.println("2 - Mostrar artículos");
            System.out.println("3 - Buscar artículo");
            System.out.println("4 - Eliminar artículo");
            System.out.println("5 - Modificar artículo");
            System.out.println("6 - Guardar");
            System.out.println("7 - Salir");
            int option = scanner.nextInt();
            scanner.nextLine();

            switch (option) {
                case 1:
                    System.out.println("Escribe el titulo del artículo: ");
                    String title = scanner.nextLine();
                    System.out.println("Escribe el contenido del artículo: ");
                    String content = scanner.nextLine();
                    Article article = new Article(title, content);
                    manager.addArticle(article);
                    break;
                case 2:
                    System.out.println("---Articulos---");
                    manager.showArticles();
                    break;
                case 3:
                    System.out.println("Escribe el título del artículo a buscar: ");
                    String search = scanner.nextLine();
                    manager.searchArticle(search);
                    break;
                case 4:
                    System.out.println("Escribe el título del artículo a eliminar");
                    String delete = scanner.nextLine();
                    manager.deleteArticle(delete);
                    break;
                case 5:
                    System.out.println("Escribe el título del artículo a modificar");
                    String modify = scanner.nextLine();

                    Article article1 = manager.findArticleByTitle(modify);

                    if (article1 == null) {
                        System.out.println("No se encontró el artículo");
                        break;
                    }else{
                        System.out.println("Escribe el título del artículo nuevo");
                        String newTitle = scanner.nextLine();
                        System.out.println("Escribe el contenido del artículo nuevo");
                        String newContent = scanner.nextLine();

                        manager.modifyArticle(article1, newTitle, newContent);
                        break;
                    }
                case 6:
                    manager.saveArticles();
                    break;
                case 7:
                    running = false;
                    break;
            }
        }
        scanner.close();
        
    }
}
