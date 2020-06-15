package sample;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;

public class Main extends Application {
    static final double WIDTH = 700, HEIGHT = 600;
    int gameScore = 0;
    Text scoreText, scoreLabel;
    private Font scoreFont;
    private boolean up, down, left, right, aKey, dKey, sKey, wKey;
    Group root;
    VBox buttonContainer;
    MainCharacter main;
    Enemy iBeagle;
    Projectile iBullet, iCheese;
    Prop iPR0, iPR1, iPR2, iPR3, iPR4, iPR5, iPR6, iPR7, iPR8;
    private Scene scene, scene0;
    private Image iB0, iB1, iB2, iB3, iB4, iB5, iB6, iB7, iB8, iP0, iT0, iT1, iE0, iC0, iC1;
    private Image backgroundImage;
    private GamePlayLoop gamePlayLoop;
    CastingDirector castDirector;
    ImageView background;
    Button playGameButton, howToPlayButton, settingButton, exitButton;

    @Override
    public void start(Stage primaryStage) throws IOException {
        // create scene 0
        Button playGameButton = new Button("PLAY GAME");
        playGameButton.setFont(new Font("Candara Bold", 35.5));

        Button howToPlayButton = new Button("How to Play");
        howToPlayButton.setFont(new Font("Candara", 35.5));

        Button settingButton = new Button("Settings");
        settingButton.setFont(new Font("Candara", 35.5));

        Button quitButton = new Button("Quit");
        quitButton.setFont(new Font("Candara", 35.5));
        buttonContainer = new VBox();

        buttonContainer.getChildren().addAll(playGameButton, howToPlayButton, settingButton, quitButton);
        buttonContainer.setSpacing(50);
        buttonContainer.setAlignment(Pos.CENTER);
        //buttonContainer = FXMLLoader.load(getClass().getResource("sample.fxml"));
        scene0 = new Scene(buttonContainer, WIDTH, HEIGHT, Color.BROWN);
        primaryStage.setTitle("RPG Game Team 17");
        playGameButton.setOnAction(actionEvent -> {
            primaryStage.setScene(scene);
        });
        root = new Group();
        scene = new Scene(root, WIDTH, HEIGHT, Color.GREEN);
        primaryStage.setScene(scene0);
        primaryStage.show();
        createSceneEventHandling();
        loadImageAssets();
        createGameActors();
        addGameActorNodes();
        createCastingDirection();
        createStartGameLoop();

    }

    public static void main(String[] args) {
        launch(args);
    }

    public boolean isUp() {
        return up;
    }

    public void setUp(boolean up) {
        this.up = up;
    }

    public boolean isDown() {
        return down;
    }

    public void setDown(boolean down) {
        this.down = down;
    }

    public boolean isLeft() {
        return left;
    }

    public void setLeft(boolean left) {
        this.left = left;
    }

    public boolean isRight() {
        return right;
    }

    public void setRight(boolean right) {
        this.right = right;
    }

    public boolean isaKey() {
        return aKey;
    }

    public void setaKey(boolean aKey) {
        this.aKey = aKey;
    }

    public boolean isdKey() {
        return dKey;
    }

    public void setdKey(boolean dKey) {
        this.dKey = dKey;
    }

    public boolean issKey() {
        return sKey;
    }

    public void setsKey(boolean sKey) {
        this.sKey = sKey;
    }

    public boolean iswKey() {
        return wKey;
    }

    public void setwKey(boolean wKey) {
        this.wKey = wKey;
    }


    private void createSceneEventHandling() {
        scene.setOnKeyPressed((KeyEvent event) -> {
            switch (event.getCode()) {
                case W:  wKey = true; break;
                case I: up = true; break;
                case S:  sKey = true; break;
                case K: down = true; break;
                case A:  aKey = true; break;
                case J: left = true; break;
                case D: dKey = true; break;
                case L: right = true; break;
            }
        });
        scene.setOnKeyReleased((KeyEvent event) -> {
            switch (event.getCode()) {
                case W:  wKey = false; break;
                case I: up = false; break;
                case S: sKey = false; break;
                case K: down = false; break;
                case A: aKey = false; break;
                case J: left = false; break;
                case D: dKey = false; break;
                case L: right = false; break;
            }
        });
    }
    private void loadImageAssets() {
        iP0 = new Image("/proper1.png", 50, 50, false, false, true);

        iB0 = new Image("/pngegg_1.png", 50, 50, false, false, true);
        iB1 = new Image("/right2.png", 50, 50, false, false, true);
        iB2 = new Image("/right1.png", 50, 50, false, false, true);
        iB3 = new Image("/behind1.png", 50, 50, false, false, true);
        iB4 = new Image("/behind2.png", 50, 50, false, false, true);
        iB5 = new Image("/front1.png",50, 50, false, false, true);
        iB6 = new Image("/front2.png", 50, 50, false, false, true);
        iB7 = new Image("/sprite7.png", 50, 50, false, false, true);
            iB8 = new Image("/sprite8.png", 50, 50, false, false, true);

        iE0 = new Image("/enemy.png", 50, 50, false, false, true);
        iC0 = new Image("/bullet.png", 64, 24, false, false, true);
        iC1 = new Image("/cheese.png", 32, 29, false, false, true);
        backgroundImage = new Image("/background4.png", 700, 600, false, false, true);
    }

    private void createGameActors() {
        main = new MainCharacter(this,
                "M58,8 L58,8 43,24 32,28 32,41 18,41 28,54 40,61 35,73 41,79 45,54 55,39 65,40 69,25 Z",
                0, 562, iB0, iB1, iB2, iB3, iB4, iB5, iB6, iB7, iB8);

        iPR0 = new Prop("M0,0 L0,32 72,32 72,0 Z", 50, 100, iP0);
        iPR1 = new Prop("M0,0 L0,32 72,32 72,0 Z", 100, 150, iP0);
        iPR2 = new Prop("M0,0 L0,32 72,32 72,0 Z", 0, 100, iP0);


        ///
        background = new ImageView(backgroundImage);


        iBeagle = new Enemy(this, "M0 6 L0 52 70 52 70 70 70 93 115 45 115 0 84 0 68 16 Z", 300, 481, iE0);
        iBullet = new Projectile("M0 4 L0 16 64 16 64 4 Z", -9, -9, iC0);
        //iCheese = new Projectile("M0 0 L0 32 32 32 32 0 Z", -32, 0, iC1);
        iCheese = new Projectile("M0 4 L0 16 64 16 64 4 Z", -96, -8, iC1);
        //iPV1 = new PropV("M150 0 L75 200 L225 200 Z", 0, -58, iP1);
        //iPR1 = new Prop("M150 0 L75 200 L225 200 Z", 0, -150, iP1);
    }

    private void addGameActorNodes() {
        // add background
        root.getChildren().add(background);
        // add prop
        root.getChildren().add(iPR0.spriteFrame);
        root.getChildren().add(iPR1.spriteFrame);
        root.getChildren().add(iPR2.spriteFrame);
        //.getChildren().add(iPR4.spriteFrame);
        //root.getChildren().add(iPR5.spriteFrame);
        //root.getChildren().add(iPR6.spriteFrame);

        // add main
        root.getChildren().add(iBeagle.spriteFrame);
        root.getChildren().add(main.spriteFrame);
        //root.getChildren().add(iCheese.spriteFrame);
        //root.getChildren().add(iBullet.spriteFrame);
    }

    private void createCastingDirection() {
        castDirector = new CastingDirector();
        castDirector.addCurrentCast(iPR0, iPR1);
    }


    private void createStartGameLoop() {
        gamePlayLoop = new GamePlayLoop(this);
        gamePlayLoop.start();
    }
}
