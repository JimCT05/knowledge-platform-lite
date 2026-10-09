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
                    String title = scanner.nextLine().trim();
                    if (title.isBlank()) {
                        System.out.println("No se puede crear un artículo sin título");
                        break;
                    }

                    System.out.println("Escribe el contenido del artículo: ");
                    String content = scanner.nextLine().trim();
                    if (content.isBlank()) {
                        System.out.println("No se puede crear un artículo sin contenido");
                        break;
                    }
                    manager.addArticle(title, content);
                    break;
                case 2:
                    System.out.println("---Articulos---");
                    manager.showArticles();
                    break;
                case 3:
                    System.out.println("Escribe el ID del artículo a buscar: ");
                    String search = scanner.nextLine();
                    if (search.isBlank()) {
                        System.out.println("No se puede buscar un artículo sin ID");
                    } else {
                        try {
                            int id = Integer.parseInt(search);
                            manager.searchArticle(id);
                        } catch (NumberFormatException e) {
                            System.out.println("ID inválido. Por favor, introduce un número.");
                        }
                    }
                    break;
                case 4:
                    System.out.println("Escribe el ID del artículo a eliminar");
                    String delete = scanner.nextLine();
                    if (delete.isBlank()) {
                        System.out.println("No se puede eliminar un artículo sin ID");
                    } else {
                        try {
                            int id = Integer.parseInt(delete);
                            manager.deleteArticle(id);
                        } catch (NumberFormatException e) {
                            System.out.println("ID inválido. Por favor, introduce un número.");
                        }
                    }
                    break;
                case 5:
                    System.out.println("Escribe el ID del artículo a modificar");
                    String modify = scanner.nextLine();
                    if (modify.isBlank()) {
                        System.out.println("No se puede modificar un artículo sin ID");
                        break;
                    }else {
                        int id;
                        try{
                             id = Integer.parseInt(modify);
                        }catch (NumberFormatException e){
                            System.out.println("ID inválido. Por favor, introduce un número.");
                            break;
                        }
                        Article foundArticle = manager.findArticleById(id);

                    if (foundArticle == null) {
                        System.out.println("No se encontró el artículo");
                        break;
                    }
                    System.out.println("Escribe el título del artículo nuevo");
                    String newTitle = scanner.nextLine().trim();
                    if (newTitle.isBlank()) {
                        System.out.println("No se puede modificar un artículo sin título");
                        break;
                    }
                    System.out.println("Escribe el contenido del artículo nuevo");
                    String newContent = scanner.nextLine().trim();
                    if (newContent.isBlank()) {
                        System.out.println("No se puede modificar un artículo sin contenido");
                        break;
                    }
                    manager.modifyArticle(foundArticle, newTitle, newContent);
                    break;
                    }
                case 6:
                    manager.saveArticles();
                    break;
                case 7:
                    running = false;
                    break;
                default:
                    System.out.println("Opción no válida");
                    break;
            }
        }
        scanner.close();
        
    }
}
