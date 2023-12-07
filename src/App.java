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
import java.util.ArrayList;
import java.util.List;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import javafx.util.Duration;


public class App extends Application {

    private static final String DATA_FILE = "user_data.txt"; // DATA
    private List<Employe> employes = new ArrayList<>(); // Liste des employées
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
		        	errorMessageText.setText("⚠ Nom d'utilisateur ou Mot de passe incorrect.");
				    for (Employe employe : employes) {
				    	
				    	// Validation mot de passe et username avec la DATA + Ouvrir le pannel en fonction du rôle
				    	if (employe.getUsername().equalsIgnoreCase(username) && employe.getPassword().equals(password)) {
						    switch (employe.getRole()) {
							    case "Cuisinier" -> openCookPanel();
		                        case "Serveur" -> openServeurPanel();
		                        case "Barman" -> openBartenderPanel();          
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
		
		Text validText = new Text();
		validText.setLayoutX(548);
		validText.setLayoutY(560);
		   
		Button CommandButton = new Button("COMMANDER");
		CommandButton.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent event) {
		       	validText.setText("✔ Stock remplis");
			}
	    });
		CommandButton.setLayoutX(548);
		CommandButton.setLayoutY(502);

		GridPane gridPane = new GridPane();
	    gridPane.setPadding(new Insets(10, 10, 10, 10));
	    gridPane.setVgap(10);
	    gridPane.setHgap(10);
	    
	    String[] imagePaths = {"images/Beef.png","images/Bread.png","images/Cheese.png","images/Garlic.png","images/Mushroom.png","images/Pan.png","images/Salad.png","images/Sausage.png","images/Tomato.png","images/Eau.png","images/Jus.png","images/Citron.png","images/Cidre.png","images/Biere.png"};
	    String[] productNames = {"Boeuf", "Pate", "Conte", "Oignon", "Enoki", "Pain", "Salade","Boudin", "Tomate", "Eau", "Jus", "Citron", "Cidre", "Biere"};
	    int[] prices = {8,2,4,3,5,2,6,8,1,0,2,2,3,7};	        
	    int[] quantities = {10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10};
	   
	    for (int i = 0; i < 14; i++) {
	    	RectangleWithProductInfo rectangle = new RectangleWithProductInfo(productNames[i], prices[i],
	        quantities[i], imagePaths[i]);
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
        StockPane.getChildren().addAll( backgroundStock, BackButton, Date, Total,validText, CommandButton, gridPane,scrollPane);    

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
    	
    	// Setup
        App.setTitle("Pepper Manager® | Recrutement"); 
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
                    employes.add(new Employe(newUser, newPassword, role, Double.parseDouble(salaireText), 0)); // Ajout du membre
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
        ImageView backgroundPlanning = new ImageView(new Image("images/BackgroundStock.png"));
        backgroundPlanning.fitWidthProperty().bind(App.widthProperty());
        backgroundPlanning.fitHeightProperty().bind(App.heightProperty());

        Text Member = new Text(employes.size() + " membres");
        Member.getStyleClass().add("text-member2");
        Member.setLayoutX(275);
        Member.setLayoutY(120);

        ListView<String> employeDispo = new ListView<>();
        employeDispo.getStyleClass().add("list2");
        for (Employe employe : employes) {
            String employeInfoUsername = employe.getUsername() + "\n" + employe.getRole();
            employeDispo.getItems().add(employeInfoUsername);
        }
        employeDispo.setLayoutX(260);
        employeDispo.setLayoutY(136);

        // Assemblage du panneau avec l'arrière-plan et les composants
        Pane PlanningPane = new Pane();
        PlanningPane.getChildren().addAll(backgroundPlanning,employeDispo,Member);
        // Application du style
        PlanningPane.getStylesheets().add("login.css");

        // Définition de la scène
        App.setScene(new Scene(PlanningPane, 800, 600));
    }


    //---------- Pepper Manager® | Statistique ----------//
    private void openStatisticPanel() {
    
    // Setup
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
       Time.setLayoutY(107);

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
       
	   Text PlatDay = new Text("99");
	   PlatDay.setLayoutX(300);
	   PlatDay.setLayoutY(330);

	   Text CocktailDay = new Text("99");
	   CocktailDay.setLayoutX(405);
	   CocktailDay.setLayoutY(330);

	   Text CoinDay = new Text("999");
	   CoinDay.setLayoutX(292);
	   CoinDay.setLayoutY(185);

	   Text ClientDay = new Text("99");
	   ClientDay.setLayoutX(405);
	   ClientDay.setLayoutY(185);
	   
	   Text validText = new Text();
	   validText.setLayoutX(278);
	   validText.setLayoutY(490);
	   
	   Button PrintButton = new Button("IMPRIMER COURSE");
	   PrintButton.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent event) {
		       	validText.setText("✔ Liste de Course Imprimée");	
			}
	   });
	   PrintButton.setLayoutX(278);
	   PrintButton.setLayoutY(425);

	   Text PlatTotal = new Text("99");
	   PlatTotal.setLayoutX(578);
	   PlatTotal.setLayoutY(330);

	   Text CocktailTotal = new Text("99");
	   CocktailTotal.setLayoutX(678);
	   CocktailTotal.setLayoutY(330);

	   Text CoinTotal = new Text("999");
	   CoinTotal.setLayoutX(563);
	   CoinTotal.setLayoutY(185);

	   Text ClientTotal = new Text("99");
	   ClientTotal.setLayoutX(678);
	   ClientTotal.setLayoutY(185);
	   
       // Pane Components
       Pane StatisticPane = new Pane();
       StatisticPane.getChildren().addAll( backgroundStatistic, BackButton, Time,PlatDay, CocktailDay, CoinDay, ClientDay,validText, PrintButton, PlatTotal,CocktailTotal, CoinTotal, ClientTotal );  

       // Style
       PlatDay.getStyleClass().add("statistique");
       CocktailDay.getStyleClass().add("statistique");
       CoinDay.getStyleClass().add("statistique");
       ClientDay.getStyleClass().add("statistique");
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
    private void openBartenderPanel() {
    
    // Setup
        App.setTitle("Pepper Barman® | Préparation"); 
        ImageView backgroundBartender = new ImageView(new Image("images/BackgroundBarman.png"));
        backgroundBartender.fitWidthProperty().bind(App.widthProperty());
        backgroundBartender.fitHeightProperty().bind(App.heightProperty());     

    // Pannel à Gauche
       Button BackButton = new Button("Retour");
       BackButton.setOnAction(e -> start(App));
       BackButton.setLayoutX(50);
       BackButton.setLayoutY(55);

    // Pane Components
        Pane BartenderPane   = new Pane();
        BartenderPane.getChildren().addAll( backgroundBartender, BackButton);    

    // Style
        BackButton.getStyleClass().add("backBarman-button");
                   
        BartenderPane.getStylesheets().add("login.css");        
        App.setScene(new Scene(BartenderPane, 800, 600));    
    }
    
    //---------- Pepper Cuisinier® | Préparation ----------//
    private void openCookPanel() {
    
    // Setup
        App.setTitle("Pepper Cuisinier® | Préparation"); 
        ImageView backgroundCook = new ImageView(new Image("images/BackgroundCuisinier.png"));
        backgroundCook.fitWidthProperty().bind(App.widthProperty());
        backgroundCook.fitHeightProperty().bind(App.heightProperty()); 
        
    // Pannel à Gauche
       Button BackButton = new Button("Retour");
       BackButton.setOnAction(e -> start(App));
       BackButton.setLayoutX(50);
       BackButton.setLayoutY(55);

    // Pane Components
       Pane CookPane   = new Pane();
       CookPane.getChildren().addAll( backgroundCook, BackButton);    

    // Style
       BackButton.getStyleClass().add("backCooker-button");
       CookPane.getStylesheets().add("login.css"); 
       
       App.setScene(new Scene(CookPane, 800, 600)); 
    }
    
    //---------- Pepper Serveur® | Réservation  ----------//
    private void openServeurPanel() {

    // Setup
        App.setTitle("Pepper Serveur® | Réservation"); 
        ImageView backgroundServeur = new ImageView(new Image("images/BackgroundServer.png"));
        backgroundServeur.fitWidthProperty().bind(App.widthProperty());
        backgroundServeur.fitHeightProperty().bind(App.heightProperty()); 
    
    // Pannel à Gauche
        Button BackButton = new Button("Retour");
        BackButton.setOnAction(e -> start(App));
        BackButton.setLayoutX(50);
        BackButton.setLayoutY(55);
        
    // Pane Components
		Button TableButton= new Button("Prendre");
		
        TextField TableInput = new TextField();
        TableInput.setPromptText("Client");
        
        VBox components = new VBox(BackButton, TableButton, TableInput);
       
        TableButton.setOnAction(e -> {
            components.getChildren().remove(TableButton);
            components.getChildren().remove(TableInput);

    		Button PayementButton = new Button("Payement");
    		PayementButton.setOnAction(e1 ->openPayementPanel());
    		Button ServiceButton = new Button("Service");
    		Button OrderButton = new Button("Order");
    		OrderButton.setOnAction(e1 ->openOrderPanel());

            components.getChildren().addAll(PayementButton, ServiceButton, OrderButton);
        });
        
        StackPane ServeurPane = new StackPane(backgroundServeur, components); 

    // Style
        BackButton.getStyleClass().add("backServeur-button");

        App.setScene(new Scene(ServeurPane, 800, 600)); 
    }
    
    //---------- Pepper Serveur® | Commander ----------//
    private void openOrderPanel() {
    
    // Setup
    	App.setTitle("Pepper Serveur® | Commander"); 
	    ImageView backgroundOrder = new ImageView(new Image("images/BackgroundCommand.png"));
        backgroundOrder.fitWidthProperty().bind(App.widthProperty());
        backgroundOrder.fitHeightProperty().bind(App.heightProperty()); 
            
    // Pannel à Gauche
        Button BackButton = new Button("Retour");
        BackButton.setOnAction(e -> openServeurPanel());
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
       			validText.setText("✔ Commande envoyée");	
       		}
       	});
       	CommandButton.setLayoutX(548);
       	CommandButton.setLayoutY(502);

       	GridPane gridPane = new GridPane();
       	gridPane.setPadding(new Insets(10, 10, 10, 10));
       	gridPane.setVgap(10);
       	gridPane.setHgap(10);
       	    
       	String[] imagePaths = {"images/Burger.png","images/Burger.png","images/Burger.png","images/Soupe.png","images/Soupe.png","images/Soupe.png","images/Salade.png","images/Salade.png","images/Salade.png","images/Pizza.png","images/Pizza.png","images/Pizza.png","images/Eau.png","images/Jus.png","images/Citron.png","images/Cidre.png","images/Biere.png"};
       	String[] productNames = {"Burger VG", "Burger CN", "Burger CL", "Soupe TM", "Soupe CP", "Soupe OG", "Salade CS","Salade NP", "Salade FR", "Pizza 4FR","Pizza FOR", "Pizza CAL", "Eau 1L", "Jus 33cl", "Citron 33cl", "Cidre 33cl", "Biere 50cl"};
       	int[] prices = {12,14,15,7,8,10,12,15,18,13,16,18,1,4,5,8,10};	        
       	   
       	for (int i = 0; i < 17; i++) {
       		RectangleWithOrder rectangle = new RectangleWithOrder(productNames[i], prices[i],imagePaths[i]);
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
    private void openPayementPanel() {
    
    // Setup
    App.setTitle("Pepper Serveur® | Paiement");
    ImageView backgroundPayement = new ImageView(new Image("images/BackgroundPayement.png"));
    backgroundPayement.fitWidthProperty().bind(App.widthProperty());
    backgroundPayement.fitHeightProperty().bind(App.heightProperty()); 
                
    // Pannel à Gauche
    Button BackButton = new Button("Retour");
    BackButton.setOnAction(e -> openServeurPanel());
    BackButton.setLayoutX(50);
    BackButton.setLayoutY(55); 

	Text ClientText = new Text("Clients: " + " personnes");
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
	public RectangleWithProductInfo(String productName,int prices, int quantity, String imagePath) {
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
}

//Commande plat rectangle
class RectangleWithOrder extends GridPane {
	public RectangleWithOrder(String productName,int prices,String imagePath) {
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