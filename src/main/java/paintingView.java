import javafx.fxml.FXMLLoader;

import java.io.IOException;

public class paintingView {

    public paintingView() {

        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("src/main/views/paintingView.fxml"));

        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);

        try {
            fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }


    }
}