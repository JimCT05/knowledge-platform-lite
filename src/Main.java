import java.util.Scanner;

public class Main {
    public static void main(String[] args){
        // Crear instancia de ArticleManager y Menu
        ArticleManager manager = new ArticleManager();
        Menu menu = new Menu();

        // Cargar artículos desde el archivo al iniciar la aplicación
        manager.loadArticles();

        Scanner scanner = new Scanner(System.in);
        boolean running = true;
        while (running) {
            // Mostrar el menú y obtener la opción del usuario
            menu.showMenu();
            int option = scanner.nextInt();
            scanner.nextLine();

            switch (option) {
                case 1:
                    System.out.println("Escribe el titulo del artículo: ");
                    String title = scanner.nextLine();
                    System.out.println("Escribe el contenido del artículo: ");
                    String content = scanner.nextLine();
                    
                    manager.addArticle(title, content);
                    break;
                case 2:
                    System.out.println("---Articulos---");
                    manager.showArticles();
                    break;
                case 3:
                    System.out.println("Escribe el ID del artículo a buscar: ");
                    String search = scanner.nextLine();
                    manager.searchArticle(Integer.parseInt(search));
                    break;
                case 4:
                    System.out.println("Escribe el ID del artículo a eliminar");
                    String delete = scanner.nextLine();
                    manager.deleteArticle(Integer.parseInt(delete));
                    break;
                case 5:
                    System.out.println("Escribe el ID del artículo a modificar");
                    String modify = scanner.nextLine();

                    Article foundArticle = manager.findArticleById(Integer.parseInt(modify));

                    if (foundArticle == null) {
                        System.out.println("No se encontró el artículo");
                        break;
                    }else{
                        System.out.println("Escribe el título del artículo nuevo");
                        String newTitle = scanner.nextLine();
                        System.out.println("Escribe el contenido del artículo nuevo");
                        String newContent = scanner.nextLine();

                        manager.modifyArticle(foundArticle, newTitle, newContent);
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
