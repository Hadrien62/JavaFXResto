import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.*;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;

import java.io.*;
import java.util.*;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import javafx.util.Duration;
import javafx.scene.control.ListCell;
import java.nio.file.Files;
import java.nio.file.Path;


public class App extends Application {

    private static final String DATA_FILE = "user_data.txt"; // DATA
    private static final String NomFichier = "employesDuJour.txt";//DATA employés du jour

    private static final String DATA_Commande = "commande.txt"; // DATA commande
    private final List<Employe> employes = new ArrayList<>(); // Liste des employés
    private final ListeCourse tmp_liste = new ListeCourse();
    private final List<Employe> employesTravail = new ArrayList<>();// Liste des employés qui travaillent ce jour
    private final List<Boisson> listeCommandeBoissons = new ArrayList<>();// Liste des boissons
    private final List<Plats> listeCommandePlats = new ArrayList<>();// Liste des plats

    private final List<Produit> listeCommandeServir = new ArrayList<>();// Liste des produits à servir
    private Stage App;

	//---------- Pepper® | Se Connecter ----------//
    public void start(Stage App) {
        this.App = App;
        
    // Setup Stage
        App.setTitle("Pepper® | Se Connecter"); // Titre
        App.getIcons().add(new Image("images/Logo.png")); // Icone
        
        ImageView backgroundLogin = new ImageView(new Image("images/LoginBackground.png")); // Image de Fond
        backgroundLogin.fitWidthProperty().bind(App.widthProperty()); // Longeur
        backgroundLogin.fitHeightProperty().bind(App.heightProperty());  // Largeur
        
        loadEmployeeData(); // Charger les modifications
    
    // Pane Components 
        TextField UsernameInput = new TextField(); // Input
        UsernameInput.setPromptText("Nom d'utilisateur"); // Placeholder
        
        PasswordField PasswordInput = new PasswordField();
        PasswordInput.setPromptText("Mot de passe");
        
		Button LoginButton = new Button("SE CONNECTER");
		
	    Text errorMessageText = new Text();

		VBox components = new VBox(UsernameInput, PasswordInput,LoginButton,errorMessageText); // Conteneur
        StackPane loginPane = new StackPane(backgroundLogin, components); 

    //---------- Méthode pour gérer la connexion suivant les rôles ----------//
	    LoginButton.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent event) {
				
				String username = UsernameInput.getText(); 
			    String password = PasswordInput.getText();

		    	// Ouvrir le pannel Manager
		        if(username.equalsIgnoreCase("a") && password.equals("az")){
		        	openManagerPanel();
		        }
		        else{
		        	errorMessageText.setText("⚠ Nom d'utilisateur ou Mot de passe incorrect ou vous n'êtes pas dans le planning.");
                    loadEmployeeDuJourData();
				    for (Employe employe : employesTravail) {
				    	
				    	// Validation mot de passe et username avec la DATA + Ouvrir le pannel en fonction du rôle
				    	if (employe.getUsername().equalsIgnoreCase(username) && employe.getPassword().equals(password)) {
						    switch (employe.getRole()) {
							    case "Cuisinier" -> openCookPanel(employe);
		                        case "Serveur" -> openServeurPanel(employe);
		                        case "Barman" -> openBartenderPanel(employe);
						    }
				        }
				    }
		        }
			}
		});

     // Style
        components.getStyleClass().add("component-box");
        UsernameInput.getStyleClass().add("text-field");
        PasswordInput.getStyleClass().add("text-field");
        LoginButton.getStyleClass().add("login-button");
        errorMessageText.getStyleClass().add("error");

     // Affichage
        loginPane.getStylesheets().add("login.css"); // Style
        App.setScene(new Scene(loginPane, 800, 600)); // Taille du panel
        App.show(); // Afficher la scène        
    }

    //---------- Pepper Manager® | Menu Principal ----------//
    private void openManagerPanel() {
   
   // Setup Stage
        App.setTitle("Pepper Manager® | Menu Principal");

    // ImageView
        ImageView backgroundManager = new ImageView(new Image("images/BackgroundManager.png"));
        backgroundManager.fitWidthProperty().bind(App.widthProperty());
        backgroundManager.fitHeightProperty().bind(App.heightProperty());

    // Boutons
        Button BackButton = new Button("Retour");
        BackButton.setOnAction(e -> start(App));
        BackButton.setLayoutX(55);
        BackButton.setLayoutY(50);
        
        Button PlanningButton = new Button("PLANNING");
        PlanningButton.setOnAction(e -> openPlanningPanel());
        PlanningButton.setLayoutX(318);
        PlanningButton.setLayoutY(210);
        
        Button RecruitmentButton = new Button("RECRUTEMENT");
        RecruitmentButton.setOnAction(e -> openRecruitmentPanel());
        RecruitmentButton.setLayoutX(573);
        RecruitmentButton.setLayoutY(210);
        
        Button StockButton = new Button("STOCK");
        StockButton.setOnAction(e -> openStockPanel());
        StockButton.setLayoutX(318);
        StockButton.setLayoutY(480);
        
        Button StatisticButton = new Button("STATISTIQUE");
        StatisticButton.setOnAction(e -> openStatisticPanel());
        StatisticButton.setLayoutX(573);
        StatisticButton.setLayoutY(480);

        Pane ManagerPane = new Pane();
        ManagerPane.getChildren().addAll(backgroundManager, BackButton, PlanningButton, RecruitmentButton, StatisticButton, StockButton);

    // Style 
        BackButton.getStyleClass().add("back-button");
        PlanningButton.getStyleClass().add("manager-button");
        RecruitmentButton.getStyleClass().add("manager-button");
        StatisticButton.getStyleClass().add("manager-button");
        StockButton.getStyleClass().add("manager-button");

        ManagerPane.getStylesheets().add("login.css"); 
        App.setScene(new Scene(ManagerPane, 800, 600));
    }

    //---------- Pepper Manager® | Gestion des Stocks ----------//
    private void openStockPanel() {
        stock tmp_stock = new stock();

        // Setup
        App.setTitle("Pepper Manager® | Gestion des Stocks ");
        ImageView backgroundStock = new ImageView(new Image("images/BackgroundStock.png"));
        backgroundStock.fitWidthProperty().bind(App.widthProperty());
        backgroundStock.fitHeightProperty().bind(App.heightProperty());

        // Pannel à Gauche
        Button BackButton = new Button("Retour");
        BackButton.setOnAction(e -> openManagerPanel());
        BackButton.setLayoutX(50);
        BackButton.setLayoutY(55);

        // Pannel à Droite
        Label Date = new Label();
        Date.setLayoutX(685);
        Date.setLayoutY(65);
        LocalDate currentDate = LocalDate.now(); // Récupérer la date actuelle
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        String formattedDate = currentDate.format(formatter);
        Date.setText(formattedDate);

        Text Total = new Text("000 €");
        Total.setLayoutX(695);
        Total.setLayoutY(462);
        ListView<String> stocklistView = new ListView<>();
        int taille_lst = tmp_liste.get_liste_course_quanitite().length;
        if(tmp_liste != null) {
            for (int i = 0; i < taille_lst; i++) {

                String stockInfo = tmp_liste.get_name_liste_course()[i] + "\n" + tmp_liste.get_prix_liste_course()[i];
                stocklistView.getItems().add(stockInfo);
            }
        }
        stocklistView.setLayoutX(540);
        stocklistView.setLayoutY(116);
        //---------- Cellule Personnalisée ----------//


            //---------- Méthode Mise à jour de la cellule ----------//





        // Style
        BackButton.getStyleClass().add("backRecrutement-button");
        stocklistView.getStyleClass().add("list");

        Text validText = new Text();
        validText.setLayoutX(548);
        validText.setLayoutY(560);


        Button CommandButton = new Button("COMMANDER");
        CommandButton.setOnAction(new EventHandler<ActionEvent>() {

            public void handle(ActionEvent event) {
                validText.setText("✔ Stock remplis");
                tmp_liste.confirme_liste_course(tmp_stock);
            }
        });
        CommandButton.setLayoutX(548);
        CommandButton.setLayoutY(502);

        GridPane gridPane = new GridPane();
        gridPane.setPadding(new Insets(10, 10, 10, 10));
        gridPane.setVgap(10);
        gridPane.setHgap(10);

        String[] imagePaths = {"images/Tomato.png","images/Beef.png","images/Pan.png","images/Cheese.png","images/Garlic.png","images/Mushroom.png","images/Salad.png","images/Sausage.png","images/Bread.png","images/Citron.png","images/Cidre.png","images/Biere.png","images/Jus.png"};
        String[] productNames = {"Tomate","Boeuf","Pain","Comté","Oignon","Enoki","Salade","Boudin","Pate","Citron","Cidre","Biere","Jus"};
        int[] prices = {8,2,4,3,5,2,6,8,1,0,2,2,3,7};
        int[] quantities = tmp_stock.get_lst_quantities();


        for (int i = 0; i < 13; i++) {
            RectangleWithProductInfo rectangle = new RectangleWithProductInfo(productNames[i], prices[i],
                    quantities[i], imagePaths[i],tmp_liste,stocklistView,tmp_liste,i+1,App);
            gridPane.add(rectangle, i % 2, i / 2);
            rectangle.getStyleClass().add("grid-cell");
        }
        gridPane.setPrefSize(230, 450);

        ScrollPane scrollPane = new ScrollPane(gridPane);
        scrollPane.setLayoutX(265);
        scrollPane.setLayoutY(105);
        scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);

        // Pane Components
        Pane StockPane  = new Pane();
        StockPane.getChildren().addAll( backgroundStock, BackButton, Date,stocklistView, Total,validText, CommandButton, gridPane,scrollPane );

        // Style
        scrollPane.getStyleClass().add("grid");
        gridPane.getStyleClass().add("grid");
        Date.getStyleClass().add("date");
        Total.getStyleClass().add("total");
        validText.getStyleClass().add("valid");
        CommandButton.getStyleClass().add("stock-button");
        BackButton.getStyleClass().add("backStock-button");


        StockPane.getStylesheets().add("login.css");
        App.setScene(new Scene(StockPane, 800, 600));
    }

    //---------- Pepper Manager® | Recrutement ----------//
    private void openRecruitmentPanel() {
    	
    	// Setup        App.setTitle("Pepper Manager® | Recrutement");
        ImageView backgroundRecruitment = new ImageView(new Image("images/BackgroundRecrutement.png"));
        backgroundRecruitment.fitWidthProperty().bind(App.widthProperty());
        backgroundRecruitment.fitHeightProperty().bind(App.heightProperty()); 
        
     // Pannel à Gauche
        Button BackButton = new Button("Retour");
        BackButton.setOnAction(e -> openManagerPanel());
        BackButton.setLayoutX(50);
        BackButton.setLayoutY(55);
        
        TextField NewUsernameInput = new TextField(); 
        NewUsernameInput.setPromptText("Nom d'utilisateur"); 
        NewUsernameInput.setLayoutX(283);
        NewUsernameInput.setLayoutY(125);
        
        PasswordField NewPasswordInput = new PasswordField();
        NewPasswordInput.setPromptText("Mot de passe");
        NewPasswordInput.setLayoutX(283);
        NewPasswordInput.setLayoutY(200);
        
        TextField NewSalaryInput = new TextField();
        NewSalaryInput.setPromptText("Salaire");
        NewSalaryInput.setLayoutX(283);
        NewSalaryInput.setLayoutY(280);

        Text Euro = new Text("€");
        Euro.setLayoutX(450);
        Euro.setLayoutY(312);
        
        ChoiceBox<String> RoleChoiceBox = new ChoiceBox<>();
        RoleChoiceBox.getItems().addAll("Cuisinier", "Barman", "Serveur");
        RoleChoiceBox.setValue(null);
        RoleChoiceBox.setLayoutX(283);
        RoleChoiceBox.setLayoutY(351);
        
		Button AddButton = new Button("AJOUTER");
		AddButton.setLayoutX(283);
		AddButton.setLayoutY(420);

	    Text errorText = new Text();
	    errorText.setLayoutX(283);
	    errorText.setLayoutY(485);

	    Text validText = new Text();
	    validText.setLayoutX(283);
	    validText.setLayoutY(485);
	    
	// Pannel à Droite
        Text Member = new Text(employes.size() + " membres");
		Member.setLayoutX(675);
		Member.setLayoutY(80);

        ListView<String> employelistView = new ListView<>();
        for (Employe employe : employes) {
            String employeInfoUsername = employe.getUsername() + "\n" + employe.getRole();
            
            employelistView.getItems().add(employeInfoUsername);
        }
        employelistView.setLayoutX(540);
        employelistView.setLayoutY(116);
    // Pane Components
        Pane RecruitmentPane = new Pane();
        RecruitmentPane.getChildren().addAll( backgroundRecruitment, BackButton, NewUsernameInput, NewPasswordInput,NewSalaryInput,Euro,RoleChoiceBox,AddButton,errorText,validText,employelistView,Member);    

    //---------- Cellule Personnalisée ----------//
        employelistView.setCellFactory(param -> new ListCell<>() {
        	//---------- Méthode pour Supprimer un membre ----------//
            private final Button deleteButton = new Button();{
            	
            	// Image Poubelle Supprimer
                ImageView deleteImageView = new ImageView(new Image("images/trash.png"));
                deleteImageView.setFitWidth(16); 
                deleteImageView.setFitHeight(16);

                // Configurez le bouton avec l'image
                deleteButton.setGraphic(deleteImageView);
                deleteButton.setOnAction(event -> {
                    String username = getItem().split("\n")[0]; // Récupère le nom d'utilisateur de l'élément associé à la cellule
                    employes.removeIf(employe -> employe.getUsername().equals(username)); // Suppression en DATA
                    getListView().getItems().remove(getItem()); // Suppression de la cellule
                    Member.setText(employes.size() + " membres"); // Compteur de membre
                    saveEmployeeData();
                });
            }

            //---------- Méthode Mise à jour de la cellule ----------//
            protected void updateItem(String item, boolean empty) {
                super.updateItem(item, empty);

                if (empty || item == null) {
                    setText(null);
                    setGraphic(null);
                } 
                else {
                    int index = getIndex() + 1; 
                    HBox content = new HBox();
                    VBox Button = new VBox(); 
                    Text indexText = new Text("#" + index);

                    String[] userInfoArray = item.split("\n");
                    Label usernameLabel = new Label(userInfoArray[0]);
                    Label roleLabel = new Label(userInfoArray[1]);

                    VBox labelsVBox = new VBox(); 
                    Button.setStyle("-fx-padding: 5 5 0 5;"); 
                    labelsVBox.setStyle("-fx-padding: 5 0 0;"); 
                    labelsVBox.setSpacing(-5);
                    
                    labelsVBox.getChildren().addAll(usernameLabel, roleLabel);
                    Button.getChildren().addAll(deleteButton);
                    content.getChildren().addAll(Button,indexText,labelsVBox);
                    setGraphic(content);

                    // Style
                    indexText.getStyleClass().add("index");
                    labelsVBox.getStyleClass().add("box");
                    usernameLabel.getStyleClass().add("username");
                    roleLabel.getStyleClass().add("role");
                    deleteButton.getStyleClass().add("delete-button");
                }
            }
        });

    //---------- Méthode pour Ajouter un membre ----------//
        AddButton.setOnAction(new EventHandler<ActionEvent>() {
        	public void handle(ActionEvent event) {
            	
                String newUser = NewUsernameInput.getText(); 
                String newPassword = NewPasswordInput.getText();
                String salaireText = NewSalaryInput.getText();
                String role = RoleChoiceBox.getValue();
                String employeInfo = newUser + "\n" + role; // Affichage username et role
                
                // Vérifier si les champs sont correctements remplis
                if (salaireText.matches("^\\d*\\.?\\d+$") && !employes.stream().anyMatch(employe -> employe.getUsername().equals(newUser.toLowerCase()) || employe.getPassword().equals(newPassword.toLowerCase()) && !newUser.isEmpty()      && !newPassword.isEmpty() && role != null)) {
                	employes.removeIf(employe -> employe.getUsername().equalsIgnoreCase(newUser));
                    switch (role){
                        case "Cuisinier":
                            employes.add(new Cuisinier(newUser, newPassword, Double.parseDouble(salaireText), 0,false)); // Ajout du membre
                            break;
                        case "Serveur":
                            employes.add(new Serveur(newUser, newPassword, Double.parseDouble(salaireText), 0)); // Ajout du membre
                            break;
                        case "Barman":
                            employes.add(new Barman(newUser, newPassword, Double.parseDouble(salaireText), 0, false)); // Ajout du membre
                            break;
                    }
                    saveEmployeeData(); // Enregistrer les données           	
                	employelistView.getItems().add(employeInfo); // Ajout du membre dans la List 
                	NewUsernameInput.clear(); NewPasswordInput.clear(); NewSalaryInput.clear(); // Vider les inputs             	
                    Member.setText(employes.size() + " membres"); // Compteur de membre
                    errorText.setText("");                   
                    validText.setText("✔ Employé ajouté");             
                } 
                else {
                	errorText.setText("⚠ Erreur de saisie");
                    validText.setText("");       
                }
            }
        });

   // Style
        NewUsernameInput.getStyleClass().add("newtext-field");
        NewPasswordInput.getStyleClass().add("newtext-field");
        NewSalaryInput.getStyleClass().add("newtext-field");
        RoleChoiceBox.getStyleClass().add("role-field");
        Euro.getStyleClass().add("euro");
        AddButton.getStyleClass().add("recrutement-button");
        BackButton.getStyleClass().add("backRecrutement-button");
        Member.getStyleClass().add("text-member");
        errorText.getStyleClass().add("error");
        validText.getStyleClass().add("valid");
        employelistView.getStyleClass().add("list");
        
        RecruitmentPane.getStylesheets().add("login.css"); 
        App.setScene(new Scene(RecruitmentPane, 800, 600)); 
    }

    //---------- Méthode pour mettre à jour la Data Employé ----------//
    private void loadEmployeeData() {        
    	employes.clear(); // Videz la liste des employés existante
        try (BufferedReader reader = new BufferedReader(new FileReader(DATA_FILE))) { // Lecture du DATA
            String Separator;
            while ((Separator = reader.readLine()) != null) {
                String[] parts = Separator.split(":"); // Séparer les informations
                if (parts.length == 4) { // Vérifier le bon format (4 parties) et Récupèrer les informations de chaque partie
                    String username = parts[0];
                    String password = parts[1];
                    double salaire = Double.parseDouble(parts[2]);
                    String role = parts[3];
                    employes.add(new Employe(username, password, role, salaire, 0)); // Ajouter un membre
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Impossible de charger les données des employés.");
        }
    }

    //---------- Méthode pour Sauvegarder la Data Employé ----------//
    private void saveEmployeeData() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(DATA_FILE))) { // Ecriture du DATA
            for (Employe employe : employes) {
                writer.write(employe.getUsername() + ":" + employe.getPassword() + ":" + employe.getSalaire() + ":" + employe.getRole());
                writer.newLine(); // Ajouter une nouvelle ligne
            }
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Impossible d'enregistrer les données des employés.");
        }
    }

    //---------- Pepper Manager® | Planning ----------//
    private void openPlanningPanel() {
        App.setTitle("Pepper Manager® | Planning");

        // Création des composants
        ImageView backgroundPlanning = new ImageView(new Image("images/BackgroundPlanning.png"));
        backgroundPlanning.fitWidthProperty().bind(App.widthProperty());
        backgroundPlanning.fitHeightProperty().bind(App.heightProperty());
        //Panel de Gauche
        Button BackButton = new Button("Retour");
        BackButton.setOnAction(e -> openManagerPanel());
        BackButton.setLayoutX(50);
        BackButton.setLayoutY(55);

        Text Member = new Text(employes.size() + " membres");
        Member.getStyleClass().add("text-member2");
        Member.setLayoutX(275);
        Member.setLayoutY(120);

        ListView<String> employeDispo = new ListView<>();
        employeDispo.getStyleClass().add("list2");
        employeDispo.setCellFactory(param -> createCustomListCell());
        loadEmployeeDuJourData();
        for (Employe employe : employes) {
            String employeInfoUsername = employe.getUsername() + "\n" + employe.getRole();
            employeDispo.getItems().add(employeInfoUsername);

        }
        employeDispo.setLayoutX(260);
        employeDispo.setLayoutY(136);

        ListView<String> employeDuJour = new ListView<>();
        employeDuJour.getStyleClass().add("list2");
        for(Employe employe : employesTravail){
            String employeTaffInfoUsername = employe.getUsername() + "\n" + employe.getRole();
            employeDuJour.getItems().add(employeTaffInfoUsername);
        }
        employeDuJour.setLayoutX(525);
        employeDuJour.setLayoutY(136);
        Text AddText = new Text();
        AddText.setLayoutX(280);
        AddText.setLayoutY(560);


        Button selectButton = new Button("AJOUTER");
        selectButton.setOnAction(e -> {
            // Obtenir l'élément sélectionné
            String selectedEmployee = employeDispo.getSelectionModel().getSelectedItem();

            if (selectedEmployee != null) {
                // Ajouter l'employé à la liste employesTravail
                employesTravail.add(findEmployeeByUsername(selectedEmployee));
                // Supprimer l'employé de la liste employes
                employes.removeIf(employe -> (employe.getUsername() + "\n" + employe.getRole()).equals(selectedEmployee));
                // Mettre à jour les listes dans les ListViews
                updateEmployeeLists(employeDispo, employeDuJour);
                AddText.setText("✔ Employé ajouté");
            }
        });
        selectButton.setLayoutX(274);
        selectButton.setLayoutY(502);

        Button confirmerEquipe = new Button("VALIDER");
        Text validText = new Text();
        validText.setLayoutX(548);
        validText.setLayoutY(560);
        confirmerEquipe.setOnAction(action ->{
                try {
                    // Vérifier si le fichier existe, sinon le créer
                    Path fichierPath = Path.of(NomFichier);
                    if (!Files.exists(fichierPath)) {
                        Files.createFile(fichierPath);
                    }

                    // Utiliser BufferedWriter pour écrire dans le fichier
                    try (BufferedWriter writer = new BufferedWriter(new FileWriter(NomFichier))) {
                        for (Employe employe : employesTravail) {
                            writer.write(employe.getUsername() + ":" + employe.getPassword() + ":" + employe.getSalaire() + ":" + employe.getRole());
                            writer.newLine(); // Ajouter une nouvelle ligne
                        }

                        System.out.println("Données écrites avec succès dans le fichier : " + NomFichier);
                        try (BufferedWriter writer2 = new BufferedWriter(new FileWriter(DATA_FILE, false))) {
                            for (Employe employe : employes) {
                                writer2.write(employe.getUsername() + ":" + employe.getPassword() + ":" + employe.getSalaire() + ":" + employe.getRole());
                                writer2.newLine(); // Ajouter une nouvelle ligne
                            }

                            System.out.println("Données écrites avec succès dans le fichier : " + DATA_FILE);
                            validText.setText("✔ Planning terminé");
                        } catch (IOException e) {
                            e.printStackTrace();
                            // Gérer l'exception selon votre logique d'application
                        }
                    }
                } catch (IOException e) {
                    System.err.println("Erreur lors de la manipulation du fichier : " + e.getMessage());
                }
        });
        confirmerEquipe.setLayoutX(537);
        confirmerEquipe.setLayoutY(502);

        // Assemblage du panneau avec l'arrière-plan et les composants
        Pane PlanningPane = new Pane();
        PlanningPane.getChildren().addAll(backgroundPlanning,BackButton,AddText,employeDispo,Member,employeDuJour,selectButton,confirmerEquipe,validText);
        BackButton.getStyleClass().add("backRecrutement-button");
        selectButton.getStyleClass().add("stock-button");
        AddText.getStyleClass().add("valid");
        confirmerEquipe.getStyleClass().add("stock-button");
        validText.getStyleClass().add("valid");
        // Application du style
        PlanningPane.getStylesheets().add("login.css");

        // Définition de la scène
        App.setScene(new Scene(PlanningPane, 800, 600));
    }

    //Fonction pour récupérer les données des employés dans le fichier txt
    private void loadEmployeeDuJourData() {
        employesTravail.clear(); // Videz la liste des employés existante
        File fichier = new File(NomFichier);

        // Vérifier si le fichier existe avant de tenter de le lire
        if (!fichier.exists()) {
            System.out.println("Le fichier n'existe pas. Aucune donnée d'employé chargée.");
            return; // Sortir de la méthode si le fichier n'existe pas
        }

        try (BufferedReader reader = new BufferedReader(new FileReader(fichier))) { // Lecture du DATA
            String Separator;
            while ((Separator = reader.readLine()) != null) {
                String[] parts = Separator.split(":"); // Séparer les informations
                if (parts.length == 4) { // Vérifier le bon format (4 parties) et Récupèrer les informations de chaque partie
                    String username = parts[0];
                    String password = parts[1];
                    double salaire = Double.parseDouble(parts[2]);
                    String role = parts[3];
                    employesTravail.add(new Employe(username, password, role, salaire, 0)); // Ajouter un membre
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Impossible de charger les données des employés.");
        }
    }


    // Fonction pour créer une cellule personnalisée pour la ListView
    private ListCell<String> createCustomListCell() {
        return new ListCell<>() {
            @Override
            protected void updateItem(String item, boolean empty) {
                super.updateItem(item, empty);
                if (item == null || empty) {
                    setText(null);
                    setDisable(false);
                    setGraphic(null);
                    setStyle("-fx-control-inner-background: white;"); // Fond blanc par défaut
                } else {
                    String[] userInfo = item.split("\n");
                    String username = userInfo[0];

                    Employe employe = findEmployeeByUsername(username);
                    if (employe != null && employe.getNombreDeSoir() == 3) {
                        setStyle("-fx-control-inner-background: #d3d3d3;"); // Fond gris
                        setDisable(true); // Désactiver la sélection
                    } else {
                        setStyle("-fx-control-inner-background: white;"); // Fond blanc
                        setDisable(false); // Activer la sélection
                    }

                    setText(item);

                    // Changer la couleur du fond en rouge lorsque la cellule est sélectionnée
                    selectedProperty().addListener((obs, wasSelected, isNowSelected) -> {
                        if (isNowSelected) {
                            setStyle("-fx-text-fill: red;"); // Fond rouge
                            System.out.println("bonjour");
                        } else {
                            // Rétablir la couleur du fond normale lorsque la cellule n'est pas sélectionnée
                            if (employe != null && employe.getNombreDeSoir() == 3) {
                                setStyle("-fx-control-inner-background: #d3d3d3;"); // Fond gris
                            } else {
                                setStyle("-fx-control-inner-background: white;"); // Fond blanc
                            }
                        }
                    });
                }
            }
        };
    }


    // Fonction pour trouver un employé par son nom d'utilisateur
    private Employe findEmployeeByUsername(String username) {
        for (Employe employe : employes) {
            if ((employe.getUsername() + "\n" + employe.getRole()).equals(username)) {
                return employe;
            }
        }
        return null;
    }

    // Fonction pour mettre à jour les ListViews
    private void updateEmployeeLists(ListView<String> employeDispo, ListView<String> employeDuJour) {
        employeDispo.getItems().clear();
        employeDuJour.getItems().clear();

        for (Employe employe : employes) {
            String employeInfoUsername = employe.getUsername() + "\n" + employe.getRole();
            employeDispo.getItems().add(employeInfoUsername);
        }
        for (Employe employe : employesTravail) {
            try{
                String employeTaffInfoUsername = employe.getUsername() + "\n" + employe.getRole();
                employeDuJour.getItems().add(employeTaffInfoUsername);
            }
            catch (Exception e){
                System.out.println("Pas d'employé dans la liste");
            }
        }
    }


    //---------- Pepper Manager® | Statistique ----------//
    private void openStatisticPanel() {

        // Setup
        Stat tmp_stat  = new Stat();
        App.setTitle("Pepper Manager® | Statistique");
        ImageView backgroundStatistic = new ImageView(new Image("images/BackgroundStatistic.png"));
        backgroundStatistic.fitWidthProperty().bind(App.widthProperty());
        backgroundStatistic.fitHeightProperty().bind(App.heightProperty());

        // Pannel à Gauche
        Button BackButton = new Button("Retour");
        BackButton.setOnAction(e -> openManagerPanel());
        BackButton.setLayoutX(50);
        BackButton.setLayoutY(55);

        Label Time = new Label();
        Time.setLayoutX(325);
        Time.setLayoutY(111);

        Timeline timeline = new Timeline(
                new KeyFrame(Duration.seconds(1), event -> {
                    LocalDateTime currentDateTime = LocalDateTime.now();
                    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss dd/MM/yyyy");
                    String formattedDateTime = currentDateTime.format(formatter);
                    Time.setText(formattedDateTime);
                })
        );

        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.play();

        Text validText = new Text();
        validText.setLayoutX(280);
        validText.setLayoutY(490);

        Button PrintButton = new Button("IMPRIMER COURSE");
        PrintButton.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent event) {
                tmp_liste.ecrire_Map_Fichier_automatique("ListeAutomatique.txt");

                validText.setText("✔ Liste de Course Imprimée");
            }
        });
        PrintButton.setLayoutX(280);
        PrintButton.setLayoutY(425);

        Text PlatTotal = new Text(""+tmp_stat.getNb_plat_vendu());
        PlatTotal.setLayoutX(578);
        PlatTotal.setLayoutY(330);

        Text CocktailTotal = new Text(""+tmp_stat.getNb_cocktail_vendu());
        CocktailTotal.setLayoutX(678);
        CocktailTotal.setLayoutY(330);

        Text CoinTotal = new Text(""+tmp_stat.getChiffre_d_affaire());
        CoinTotal.setLayoutX(563);
        CoinTotal.setLayoutY(185);

        Text ClientTotal = new Text(""+tmp_stat.getNb_client());
        ClientTotal.setLayoutX(678);
        ClientTotal.setLayoutY(185);

        // Pane Components
        Pane StatisticPane = new Pane();
        StatisticPane.getChildren().addAll( backgroundStatistic, BackButton, Time,validText, PrintButton, PlatTotal,CocktailTotal, CoinTotal, ClientTotal );

        // Style
        PlatTotal.getStyleClass().add("statistique");
        CocktailTotal.getStyleClass().add("statistique");
        CoinTotal.getStyleClass().add("statistique");
        ClientTotal.getStyleClass().add("statistique");
        BackButton.getStyleClass().add("backStatistic-button");
        PrintButton.getStyleClass().add("print-button");
        validText.getStyleClass().add("valid");

        StatisticPane.getStylesheets().add("login.css");
        App.setScene(new Scene(StatisticPane, 800, 600));
    }


    //---------- Pepper Barman® | Préparation ----------//
    private void openBartenderPanel(Employe employe) {
    
    // Setup
        App.setTitle("Pepper Barman® | Préparation | " + employe.getUsername());
        ImageView backgroundBartender = new ImageView(new Image("images/BackgroundBarman.png"));
        backgroundBartender.fitWidthProperty().bind(App.widthProperty());
        backgroundBartender.fitHeightProperty().bind(App.heightProperty());     

    // Pannel à Gauche
       Button BackButton = new Button("Retour");
       BackButton.setOnAction(e -> start(App));
       BackButton.setLayoutX(50);
       BackButton.setLayoutY(55);

        ListView<String> listEnPrep = new ListView<>();
        listEnPrep.getStyleClass().add("list2");
        listEnPrep.setCellFactory(param -> createCustomListCell2());
        loadCommandeDataBoisson();
        for (Boisson boisson : listeCommandeBoissons) {
            String boissonInfo = "Table N°: " + boisson.getNumTable() + "\n" + boisson.getNom() + "\n" + boisson.getTemps_prep();
            listEnPrep.getItems().add(boissonInfo);

        }
        listEnPrep.setLayoutX(260);
        listEnPrep.setLayoutY(120);
        listEnPrep.setPrefSize(250, 350);

        // Bouton pour valider la préparation
        Button validerButton = new Button("Valider");
        validerButton.setOnAction(e -> {
            String selectedPlat = listEnPrep.getSelectionModel().getSelectedItem();
            if (selectedPlat != null) {
                listEnPrep.getItems().remove(selectedPlat);
                listeCommandeBoissons.removeIf(plat -> (plat.getNum_produit() + " " + plat.getNom() + "\n" + plat.getTemps_prep()).equals(selectedPlat));
                listeCommandeServir.add(new Plats(Integer.parseInt(selectedPlat.split(" ")[0])));
            }
        });
        validerButton.setLayoutX(260);
        validerButton.setLayoutY(500);

    // Pane Components
        Pane BartenderPane   = new Pane();
        BartenderPane.getChildren().addAll( backgroundBartender, BackButton,listEnPrep, validerButton);

    // Style
        BackButton.getStyleClass().add("backBarman-button");
                   
        BartenderPane.getStylesheets().add("login.css");        
        App.setScene(new Scene(BartenderPane, 800, 600));    
    }
    private void loadCommandeDataBoisson() {
        try (BufferedReader reader = new BufferedReader(new FileReader(DATA_Commande))) {
            String numTable;
            while ((numTable = reader.readLine()) != null) {
                int tableNumber = Integer.parseInt(numTable);

                String boissonInfo;
                while ((boissonInfo = reader.readLine()) != null && boissonInfo.length() > 0) {
                    String[] parts = boissonInfo.split(":");
                    if (parts.length == 2) {
                        int identifiantBoisson = Integer.parseInt(parts[0]);
                        boolean boissonPret = Boolean.parseBoolean(parts[1]);

                        if (identifiantBoisson > 11) {
                            Boisson boisson = new Boisson(identifiantBoisson);
                            boisson.setPret(boissonPret);
                            boisson.setNumTable(tableNumber);
                            listeCommandeBoissons.add(boisson);
                        }
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Impossible de charger les données des employés.");
        }
    }
    
    //---------- Pepper Cuisinier® | Préparation ----------//
    private void openCookPanel(Employe employe) {
    
    // Setup
        App.setTitle("Pepper Cuisinier® | Préparation | " + employe.getUsername());
        ImageView backgroundCook = new ImageView(new Image("images/BackgroundCuisinier.png"));
        backgroundCook.fitWidthProperty().bind(App.widthProperty());
        backgroundCook.fitHeightProperty().bind(App.heightProperty()); 
        
    // Pannel à Gauche
       Button BackButton = new Button("Retour");
       BackButton.setOnAction(e -> start(App));
       BackButton.setLayoutX(50);
       BackButton.setLayoutY(55);
    // Liste des commandes à préparer
        ListView<String> listEnPrep = new ListView<>();
        listEnPrep.getStyleClass().add("list3");
        listEnPrep.setCellFactory(param -> createCustomListCell2());
        loadCommandeDataPlats();
        for (Plats plat : listeCommandePlats) {
            String platInfo = "Table N°: " + plat.getNumTable() + "\n" + plat.getNom() + "\n" + plat.getTemps_prep();
            listEnPrep.getItems().add(platInfo);

        }
        listEnPrep.setLayoutX(260);
        listEnPrep.setLayoutY(120);
        listEnPrep.setPrefSize(250, 350);

    // Bouton pour valider la préparation
        Button validerButton = new Button("Valider");
        validerButton.setOnAction(e -> {
            String selectedPlat = listEnPrep.getSelectionModel().getSelectedItem();
            if (selectedPlat != null) {
                listEnPrep.getItems().remove(selectedPlat);
                listeCommandePlats.removeIf(plat -> (plat.getNum_produit() + " " + plat.getNom() + "\n" + plat.getTemps_prep()).equals(selectedPlat));
                listeCommandeServir.add(new Plats(Integer.parseInt(selectedPlat.split(" ")[0])));
            }
        });
        validerButton.setLayoutX(260);
        validerButton.setLayoutY(500);

    // Pane Components
       Pane CookPane   = new Pane();
       CookPane.getChildren().addAll( backgroundCook, BackButton, listEnPrep, validerButton);

    // Style
       BackButton.getStyleClass().add("backCooker-button");
       validerButton.getStyleClass().add("stock-button");
       CookPane.getStylesheets().add("login.css"); 
       
       App.setScene(new Scene(CookPane, 800, 600)); 
    }
    private ListCell<String> createCustomListCell2() {
        return new ListCell<>() {
            @Override
            protected void updateItem(String item, boolean empty) {
                super.updateItem(item, empty);
                if (item == null || empty) {
                    setText(null);
                    setDisable(false);
                    setGraphic(null);
                    setStyle("-fx-control-inner-background: white;"); // Fond blanc par défaut
                } else {
                    String[] userInfo = item.split("\n");
                    String username = userInfo[0];

                    Employe employe = findEmployeeByUsername(username);
                    if (employe != null && employe.getNombreDeSoir() == 3) {
                        setStyle("-fx-control-inner-background: #d3d3d3;"); // Fond gris
                        setDisable(true); // Désactiver la sélection
                    } else {
                        setStyle("-fx-control-inner-background: white;"); // Fond blanc
                        setDisable(false); // Activer la sélection
                    }

                    setText(item);
                    {
                        // Ajustez la taille de la cellule ici
                        setPrefHeight(80);
                    }

                    // Changer la couleur du fond en rouge lorsque la cellule est sélectionnée
                    selectedProperty().addListener((obs, wasSelected, isNowSelected) -> {
                        if (isNowSelected) {
                            setStyle("-fx-text-fill: red;"); // Fond rouge
                            System.out.println("bonjour");
                        } else {
                            // Rétablir la couleur du fond normale lorsque la cellule n'est pas sélectionnée
                            if (employe != null && employe.getNombreDeSoir() == 3) {
                                setStyle("-fx-control-inner-background: #d3d3d3;"); // Fond gris
                            } else {
                                setStyle("-fx-control-inner-background: white;"); // Fond blanc
                            }
                        }
                    });
                }
            }
        };
    }

    private void loadCommandeDataPlats() {
        try (BufferedReader reader = new BufferedReader(new FileReader(DATA_Commande))) {
            String numTable;
            while ((numTable = reader.readLine()) != null) {
                int tableNumber = Integer.parseInt(numTable);

                String platInfo;
                while ((platInfo = reader.readLine()) != null && platInfo.length() > 0) {
                    String[] parts = platInfo.split(":");
                    if (parts.length == 2) {
                        int identifiantPlat = Integer.parseInt(parts[0]);
                        boolean platPret = Boolean.parseBoolean(parts[1]);

                        if (identifiantPlat <= 11) {
                            Plats plat = new Plats(identifiantPlat);
                            plat.setPret(platPret);
                            plat.setNumTable(tableNumber);
                            listeCommandePlats.add(plat);
                        }
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Impossible de charger les données des employés.");
        }
    }


    
    //---------- Pepper Serveur® | Réservation  ----------//
    private void openServeurPanel(Employe employe) {

        // Setup
        App.setTitle("Pepper Serveur® | Réservation");
        ImageView backgroundServeur = new ImageView(new Image("images/BackgroundServer.png"));
        backgroundServeur.fitWidthProperty().bind(App.widthProperty());
        backgroundServeur.fitHeightProperty().bind(App.heightProperty());

        // Pannel à Gauche
        Button BackButton = new Button("Retour");
        BackButton.setOnAction(e -> start(App));
        BackButton.setLayoutX(50);
        BackButton.setLayoutY(40);

        GridPane Table1 = createReusableGridPane("Hadrien", "0", employe);
        Table1.setLayoutX(50);
        Table1.setLayoutY(100);

        GridPane Table2 = createReusableGridPane("Matt", "0", employe);
        Table2.setLayoutX(50);
        Table2.setLayoutY(300);

        // Pane Components
        Pane ServeurPane = new Pane();
        ServeurPane.getChildren().addAll(backgroundServeur, BackButton, Table1, Table2);

        // Style
        BackButton.getStyleClass().add("backServeur-button");
        ServeurPane.getStylesheets().add("login.css");

        // Scene
        App.setScene(new Scene(ServeurPane, 800, 600));
    }

    private GridPane createReusableGridPane(String Serveur, String Client, Employe employe) {
        GridPane gridPaneClient = new GridPane();
        gridPaneClient.setHgap(10);
        gridPaneClient.setVgap(10);

        Text ServeurText = new Text("Serveur: " + Serveur);
        gridPaneClient.add(ServeurText, 0, 1);

        Text ClientText = new Text("Client: " + Client);
        gridPaneClient.add(ClientText, 0, 2);

        TextField InputClient = new TextField();
        InputClient.setPromptText("Client");
        gridPaneClient.add(InputClient, 0, 3);

        Button ButtonClient = new Button("Submit");
        gridPaneClient.add(ButtonClient, 1, 3);

        ButtonClient.setOnAction(e -> {
            String userInput = InputClient.getText();
            if (isNumeric(userInput)) {
                System.out.println(userInput);
                int number = Integer.parseInt(userInput);
                if (number >= 1 && number <= 20) {
                    // Mettre à jour le texte existant
                    ClientText.setText("Client: " + InputClient.getText());

                    // Supprimer l'ancien champ de texte et le bouton
                    gridPaneClient.getChildren().removeAll(InputClient, ButtonClient);

                    // Ajouter de nouveaux éléments
                    Button paymentButton = new Button("Paiement");
                    gridPaneClient.add(paymentButton, 0, 3);
                    paymentButton.setOnAction(e1 -> openPayementPanel(employe));

                    Button serviceButton = new Button("Service");
                    gridPaneClient.add(serviceButton, 1, 3);

                    Button orderButton = new Button("Order");
                    gridPaneClient.add(orderButton, 2, 3);
                    orderButton.setOnAction(e1 -> openOrderPanel(employe));
                }
            }
        });

        return gridPaneClient;
    }


    private boolean isNumeric(String str) {
        try {
            Integer.parseInt(str);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }
    
    //---------- Pepper Serveur® | Commander ----------//
    private void openOrderPanel(Employe employe) {
        Commande Current_Commande= new Commande(1001);
    // Setup
    	App.setTitle("Pepper Serveur® | Commander"); 
	    ImageView backgroundOrder = new ImageView(new Image("images/BackgroundCommand.png"));
        backgroundOrder.fitWidthProperty().bind(App.widthProperty());
        backgroundOrder.fitHeightProperty().bind(App.heightProperty()); 
            
    // Pannel à Gauche
        Button BackButton = new Button("Retour");
        BackButton.setOnAction(e -> openServeurPanel(employe));
        BackButton.setLayoutX(50);
        BackButton.setLayoutY(55);

    // Pannel à Droite
        Text Total = new Text("000 €");
       	Total.setLayoutX(695);
       	Total.setLayoutY(462);
       		
       	Text validText = new Text();
       	validText.setLayoutX(548);
       	validText.setLayoutY(560);
       		   
       	Button CommandButton = new Button("COMMANDER");
        CommandButton.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent event) {
                Current_Commande.envoyerCommande();
                validText.setText("✔ Commande envoyée");
            }
        });
       	CommandButton.setLayoutX(548);
       	CommandButton.setLayoutY(502);

       	GridPane gridPane = new GridPane();
       	gridPane.setPadding(new Insets(10, 10, 10, 10));
       	gridPane.setVgap(10);
       	gridPane.setHgap(10);

        stock stock_tmp = new stock();
        menu menu_actuel = new menu();
        menu_actuel.actualiser_menu(stock_tmp);
        int size_menu = menu_actuel.getLst_boissons().size() + menu_actuel.getLst_plats().size();
        List<Integer> plat_possible = new ArrayList<>();
        System.out.println("numéro de Plat:");
        for (int i = 0; i < menu_actuel.getLst_plats().size(); i++) {
            System.out.println(menu_actuel.getLst_plats().get(i).getNum_produit());
            plat_possible.add(menu_actuel.getLst_plats().get(i).getNum_produit());
        }
        System.out.println("numéro de Boisson:");
        for (int i = 0; i < menu_actuel.getLst_boissons().size(); i++) {
            System.out.println(menu_actuel.getLst_boissons().get(i).getNum_produit());
            plat_possible.add(menu_actuel.getLst_boissons().get(i).getNum_produit());
        }

        String[] imagePaths = {"images/Salade.png","images/Salade.png","images/Soupe.png","images/Soupe.png","images/Soupe.png","images/Burger.png","images/Burger.png","images/Burger.png","images/Pizza.png","images/Pizza.png","images/Pizza.png","images/Citron.png","images/Cidre.png","images/Biere.png","images/Jus.png","images/Eau.png"};
        String[] productNames = {"Salade AT", "Salade ST", "Soupe OI", "Soupe TM", "Soupe CP", "Burger TS","Burger S", "Burger V", "Pizza FT","Pizza CP", "Pizza SA","Limonade 33cl","Cidre 33cl","Bière 50cl","Jus 33cl", "Eau 1L"};
        int[] prices = {9,9,8,8,8,15,15,15,12,12,12,4,5,5,1,0};
       	   
       	for (int i = 0; i < menu_actuel.getLst_plats().size(); i++) {
               System.out.println(productNames[menu_actuel.getLst_plats().get(i).getNum_produit()]);
            RectangleWithOrder rectangle = new RectangleWithOrder(productNames[menu_actuel.getLst_plats().get(i).getNum_produit()-1], prices[menu_actuel.getLst_plats().get(i).getNum_produit()-1],imagePaths[menu_actuel.getLst_plats().get(i).getNum_produit()-1],i,Current_Commande,Total);
       	    gridPane.add(rectangle, i % 2, i / 2);
       	    rectangle.getStyleClass().add("grid-cell");
       	}
        for (int i = 0; i < menu_actuel.getLst_boissons().size(); i++) {
            System.out.println(productNames[menu_actuel.getLst_boissons().get(i).getNum_produit()]);
            RectangleWithOrder rectangle = new RectangleWithOrder(productNames[menu_actuel.getLst_boissons().get(i).getNum_produit()-1], prices[menu_actuel.getLst_boissons().get(i).getNum_produit()-1],imagePaths[menu_actuel.getLst_boissons().get(i).getNum_produit()-1],i+11,Current_Commande,Total);
            gridPane.add(rectangle, (i+menu_actuel.getLst_plats().size()) % 2, (i+menu_actuel.getLst_plats().size())  / 2);
            rectangle.getStyleClass().add("grid-cell");
        }
        RectangleWithOrder rectangle = new RectangleWithOrder(productNames[15], prices[15],imagePaths[15],16 ,Current_Commande,Total);
        gridPane.add(rectangle, size_menu % 2, size_menu  / 2);
        rectangle.getStyleClass().add("grid-cell");
       	gridPane.setPrefSize(230, 450);
       	    
       	ScrollPane scrollPane = new ScrollPane(gridPane);
       	scrollPane.setLayoutX(265);
       	scrollPane.setLayoutY(105);
       	scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER); 
       	scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.NEVER); 

    // Pane Components
        Pane OrderPane   = new Pane();
        OrderPane.getChildren().addAll( backgroundOrder, BackButton, Total,validText, CommandButton, gridPane,scrollPane);    
           
    // Style
        BackButton.getStyleClass().add("backOrder-button");
        OrderPane.getStylesheets().add("login.css"); 
        scrollPane.getStyleClass().add("grid");
        gridPane.getStyleClass().add("grid");
        Total.getStyleClass().add("total");
        validText.getStyleClass().add("valid");
        CommandButton.getStyleClass().add("stock-button");
        BackButton.getStyleClass().add("backStock-button");
           
        App.setScene(new Scene(OrderPane, 800, 600)); 
    }
    
    //---------- Pepper Serveur® | Paiement  ----------//
    private void openPayementPanel(Employe employe, String NbrClient) {

        Transaction transaction = new Transaction();
    
    // Setup
    App.setTitle("Pepper Serveur® | Paiement");
    ImageView backgroundPayement = new ImageView(new Image("images/BackgroundPayement.png"));
    backgroundPayement.fitWidthProperty().bind(App.widthProperty());
    backgroundPayement.fitHeightProperty().bind(App.heightProperty()); 
                
    // Pannel à Gauche
    Button BackButton = new Button("Retour");
    BackButton.setOnAction(e -> openServeurPanel(employe));
    BackButton.setLayoutX(50);
    BackButton.setLayoutY(55); 

	Text ClientText = new Text("Clients: " + NbrClient + " personnes");
	ClientText.setLayoutX(280);
	ClientText.setLayoutY(125);

    TextField PayementInput = new TextField();
    PayementInput.setPromptText("Nombre de client");
	PayementInput.setLayoutX(281);
	PayementInput.setLayoutY(138);

	Text InfoText = new Text();
	InfoText.setLayoutX(280);
	InfoText.setLayoutY(340);
	
	Button SeparateButton = new Button("SEPARER");
	SeparateButton.setLayoutX(280);
	SeparateButton.setLayoutY(210);
	SeparateButton.setOnAction(new EventHandler<ActionEvent>() {

		public void handle(ActionEvent event) {
	       	InfoText.setText("✔ payement séparé");	
		}
    });

	Button CancelButton = new Button("ANNULER");
	CancelButton.setLayoutX(280);
	CancelButton.setLayoutY(275);
	CancelButton.setOnAction(new EventHandler<ActionEvent>() {
		public void handle(ActionEvent event) {
	       	InfoText.setText("✔ séparation annulée");	
		}
    });

    // Pannel à Droite
    Label Date = new Label();
	Date.setLayoutX(685);
	Date.setLayoutY(65);
    LocalDate currentDate = LocalDate.now(); // Récupérer la date actuelle
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    String formattedDate = currentDate.format(formatter);
    Date.setText(formattedDate);

	Text ServeurText = new Text("Serveur: " + "");
	ServeurText.setLayoutX(543);
	ServeurText.setLayoutY(125);

	Text TableText = new Text("#T" + "");
	TableText.setLayoutX(730);
	TableText.setLayoutY(125);
	
	Text TotalRestant = new Text("000 €");
	TotalRestant.setLayoutX(707);
	TotalRestant.setLayoutY(417);
	
	Text Total = new Text("000 €");
	Total.setLayoutX(695);
	Total.setLayoutY(462);
	
	Text validText = new Text();
	validText.setLayoutX(548);
	validText.setLayoutY(560);
	   
	Button PayementButton = new Button("PAYER");
	PayementButton.setOnAction(new EventHandler<ActionEvent>() {
		public void handle(ActionEvent event) {
	       	validText.setText("✔ payement accepté");	
		}
    });
	PayementButton.setLayoutX(548);
	PayementButton.setLayoutY(502);
	
    // Pane Components
    Pane PayementPane   = new Pane();
    PayementPane.getChildren().addAll( backgroundPayement, BackButton ,ClientText, PayementInput, InfoText,SeparateButton, CancelButton,Date, ServeurText, TableText, TotalRestant, Total, validText,PayementButton);    
           
    // Style
    CancelButton.getStyleClass().add("cancel-button");
    CancelButton.getStyleClass().add("stock-button");
    SeparateButton.getStyleClass().add("stock-button");
    PayementInput.getStyleClass().add("newtext-field");	
    Date.getStyleClass().add("date");
    ServeurText.getStyleClass().add("text-payement");
    TableText.getStyleClass().add("text-payement");
    ClientText.getStyleClass().add("text-payement");
    TotalRestant.getStyleClass().add("total-restant");
    Total.getStyleClass().add("total");
    validText.getStyleClass().add("valid");
    BackButton.getStyleClass().add("backOrder-button");
    InfoText.getStyleClass().add("valid");
    PayementButton.getStyleClass().add("stock-button");
    PayementPane.getStylesheets().add("login.css"); 
    
    App.setScene(new Scene(PayementPane, 800, 600)); 
    }
}

// Stock rectangle
class RectangleWithProductInfo extends GridPane {
    public RectangleWithProductInfo(String productName,int prices, int quantity, String imagePath, ListeCourse tmp_liste_course, ListView<String> stocklistView,ListeCourse tmp_liste  ,int  num_ingredient, Stage stage) {
        ImageView imageView = new ImageView(new Image(imagePath));
        imageView.setFitWidth(80);
        imageView.setFitHeight(80);

        Text nameText = new Text(productName);
        Text quantityText = new Text("" + quantity);
        HBox nameAndQuantityBox = new HBox(10, nameText, quantityText);
        nameAndQuantityBox.setAlignment(Pos.CENTER);

        Text priceText = new Text(prices + "€");
        ImageView addImageView = new ImageView(new Image("images/add.png"));
        addImageView.setFitWidth(16);
        addImageView.setFitHeight(16);
        Button addButton = new Button("");
        addButton.setGraphic(addImageView);
        addButton.setOnAction(e -> {
            tmp_liste_course.add_quantities(num_ingredient);
            stage.getScene().getRoot().requestFocus();
            updateItemStock(stocklistView,tmp_liste );
        });
        HBox priceAndButtonBox = new HBox(10, priceText, addButton);
        priceAndButtonBox.setAlignment(Pos.CENTER);

        // Pane Components
        this.add(imageView, 1, 0);
        this.add(nameAndQuantityBox, 1, 1);
        this.add(priceAndButtonBox, 1, 2);
        this.setPadding(new Insets(7));
        this.setVgap(5);
        this.setHgap(5);

        // Style
        quantityText.getStyleClass().add("name-text");
        nameText.getStyleClass().add("name-text");
        priceText.getStyleClass().add("name-text");
        addButton.getStyleClass().add("add-button");
    }
    private void updateItemStock(ListView<String> stocklistView,ListeCourse tmp_liste) {
        int taille_lst = tmp_liste.get_liste_course_quanitite().length;

        if(tmp_liste != null) {
            for (int i = 0; i < taille_lst; i++) {

                String stockInfo = tmp_liste.get_name_liste_course()[i] + "\n" + tmp_liste.get_prix_liste_course()[i];
                stocklistView.getItems().add(stockInfo);
            }
        }
    }

}

//Commande plat rectangle
class RectangleWithOrder extends GridPane {
    public RectangleWithOrder(String productName,int prices,String imagePath, int i, Commande Current_Commande, Text Total ) {
		 ImageView imageView = new ImageView(new Image(imagePath));
		 imageView.setFitWidth(80);
		 imageView.setFitHeight(80);

		 Text nameText = new Text(productName); 
		 Text priceText = new Text(prices + "€");
		 ImageView addImageView = new ImageView(new Image("images/add.png"));
		 addImageView.setFitWidth(16); 
		 addImageView.setFitHeight(16);
		 Button addButton = new Button("");
		 addButton.setGraphic(addImageView);
        addButton.setOnAction(e -> {

            if (i <= 10 ){
                Plats Plat_commande = new Plats(i+1);
                Current_Commande.add_Plats(Plat_commande);
            }
            else{
                Boisson Boisson_commande = new Boisson(i+1);
                Current_Commande.add_Boissons(Boisson_commande);
            }
            System.out.println("Nombre de plats dans la liste : " + Current_Commande.Plats.size() + "Nombre de Boissons dans la liste : " + Current_Commande.Boissons.size());
            for (Plats plat : Current_Commande.Plats) {
                System.out.println(plat.getNom());
            }
            for (Boisson Boissons : Current_Commande.Boissons) {
                System.out.println(Boissons.getNom());
            }

            Current_Commande.Calcule_Addition();
            Total.setText(Current_Commande.Get_addition() + "€");
        });
		 HBox priceAndButtonBox = new HBox(10, priceText, addButton); 
		 priceAndButtonBox.setAlignment(Pos.CENTER);
		
		 // Pane Components
		 this.add(imageView, 1, 0);
		 this.add(nameText, 1, 1);
		 this.add(priceAndButtonBox, 1, 2);
		 this.setPadding(new Insets(7));
		 this.setVgap(5);
		 this.setHgap(5);     
		
		 // Style
		 nameText.getStyleClass().add("name-text");
		 priceText.getStyleClass().add("name-text");
		 addButton.getStyleClass().add("add-button");
	}
}