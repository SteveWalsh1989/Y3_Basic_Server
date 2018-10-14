package Controller;

import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.layout.*;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.util.Duration;


/**
 * Player_Controller
 *
 * Contains layout and functionality for media play controls
 *
 * Play/Pause button: changes depending on media status
 * Volume slider to change volume
 * Timer slider to fast forward or rewind and display duration/time progressed
 */
@SuppressWarnings("ALL")
public class Player_Controller extends BorderPane {

    private final MediaPlayer mp;
    private final boolean repeat = false;
    private boolean stopRequested = false;
    private boolean atEndOfMedia = false;
    private Duration duration;
    private final Slider timeSlider;
    private final Label playTime;
    private final Slider volumeSlider;


    public Player_Controller(final MediaPlayer mp) {

        // Basic Layout
        this.mp = mp;
        setStyle("-fx-background-color: #bfc2c7;");
        MediaView mediaView = new MediaView(mp);
        Pane mvPane = new Pane() {                };
        mvPane.getChildren().add(mediaView);
        mvPane.setStyle("-fx-background-color: black;");
        setCenter(mvPane);

        // Controls Bar
        HBox mediaBar = new HBox();
        mediaBar.setAlignment(Pos.CENTER);
        mediaBar.setPadding(new Insets(5, 10, 5, 10));
        BorderPane.setAlignment(mediaBar, Pos.CENTER);

        final Button playButton  = new Button(">");


        // Play Button Action
        playButton.setOnAction(e -> {
            MediaPlayer.Status status = mp.getStatus();

            // checks status of media
            if (status == MediaPlayer.Status.UNKNOWN  || status == MediaPlayer.Status.HALTED)
            {
                // don't do anything in these states
                return;
            }

            // Plays if not played yet, paused or stopped
            if ( status == MediaPlayer.Status.PAUSED
                    || status == MediaPlayer.Status.READY
                    || status == MediaPlayer.Status.STOPPED)
            {
                // rewind the movie if  at the end
                if (atEndOfMedia) {
                    mp.seek(mp.getStartTime());
                    atEndOfMedia = false;
                }
                mp.play();
              // Pauses if currently playing
            } else {
              // Pauses if currently playing
                mp.pause();
            }
        });

        // checks current time media has been playing
        mp.currentTimeProperty().addListener(ov -> updateValues());

        // Change icon to || when playing media
        mp.setOnPlaying(() -> {
            if (stopRequested) {
                mp.pause();
                stopRequested = false;
            } else {
                playButton.setText("||");
            }
        });

        // change icon to > when paused
        mp.setOnPaused(() -> {
            System.out.println("Video Paused");
            playButton.setText(">");
        });


        mp.setOnReady(() -> {
            duration = mp.getMedia().getDuration();
            updateValues();
        });

        mp.setCycleCount(1);
        mp.setOnEndOfMedia(() -> {
            if (!repeat) {
                playButton.setText(">");
                stopRequested = true;
                atEndOfMedia = true;
            }
        });

        // add play button to media bar
        mediaBar.getChildren().add(playButton);

        // Add spacer
        Label spacer = new Label("   ");
        mediaBar.getChildren().add(spacer);

        // Add Time label
        Label timeLabel = new Label("Time: ");
        mediaBar.getChildren().add(timeLabel);

        // Add time slider
        timeSlider = new Slider();
        HBox.setHgrow(timeSlider, Priority.ALWAYS);
        timeSlider.setMinWidth(50);
        timeSlider.setMaxWidth(Double.MAX_VALUE);

        timeSlider.valueProperty().addListener(ov -> {
            if (timeSlider.isValueChanging()) {
                // multiply duration by percentage calculated by slider position
                mp.seek(duration.multiply(timeSlider.getValue() / 100.0));
            }
        });
        mediaBar.getChildren().add(timeSlider);

        // Add Play label
        playTime = new Label();
        playTime.setPrefWidth(130);
        playTime.setMinWidth(50);
        mediaBar.getChildren().add(playTime);

        // Add the volume label
        Label volumeLabel = new Label("Vol: ");
        mediaBar.getChildren().add(volumeLabel);

        // Add Volume slider
        volumeSlider = new Slider();
        volumeSlider.setPrefWidth(70);
        volumeSlider.setMaxWidth(Region.USE_PREF_SIZE);
        volumeSlider.setMinWidth(30);
        volumeSlider.valueProperty().addListener(ov -> {
            if (volumeSlider.isValueChanging()) {
                mp.setVolume(volumeSlider.getValue() / 100.0);
            }
        });
        mediaBar.getChildren().add(volumeSlider);


        setBottom(mediaBar);
    }


    private void updateValues() {
        if (playTime != null && timeSlider != null && volumeSlider != null) {
            Platform.runLater(() -> {
                Duration currentTime = mp.getCurrentTime();
                playTime.setText(formatTime(currentTime, duration));
                timeSlider.setDisable(duration.isUnknown());

                if (!timeSlider.isDisabled()
                        && duration.greaterThan(Duration.ZERO)
                        && !timeSlider.isValueChanging()) {
                    //noinspection deprecation
                    timeSlider.setValue(currentTime.divide(duration).toMillis()
                            * 100.0);
                }


                if (!volumeSlider.isValueChanging()) {
                    volumeSlider.setValue((int)Math.round(mp.getVolume()
                            * 100));
                }
            });
        }
    }

    /**
     * formatTime
     *
     * Formats the time duration of media playing
     *
     * @param elapsed - time passed
     * @param duration - total length of media
     * @return
     */
    @SuppressWarnings("JavaDoc")
    private static String formatTime(Duration elapsed, Duration duration) {
        int intElapsed = (int)Math.floor(elapsed.toSeconds());
        int elapsedHours = intElapsed / (60 * 60);
        if (elapsedHours > 0) {
            intElapsed -= elapsedHours * 60 * 60;
        }
        int elapsedMinutes = intElapsed / 60;
        int elapsedSeconds = intElapsed - elapsedHours * 60 * 60
                - elapsedMinutes * 60;

        if (duration.greaterThan(Duration.ZERO)) {
            int intDuration = (int)Math.floor(duration.toSeconds());
            int durationHours = intDuration / (60 * 60);
            if (durationHours > 0) {
                intDuration -= durationHours * 60 * 60;
            }
            int durationMinutes = intDuration / 60;
            int durationSeconds = intDuration - durationHours * 60 * 60 -
                    durationMinutes * 60;
            if (durationHours > 0) {
                return String.format("%d:%02d:%02d/%d:%02d:%02d",
                        elapsedHours, elapsedMinutes, elapsedSeconds,
                        durationHours, durationMinutes, durationSeconds);
            } else {
                return String.format("%02d:%02d/%02d:%02d",
                        elapsedMinutes, elapsedSeconds,durationMinutes,
                        durationSeconds);
            }
        } else {
            if (elapsedHours > 0) {
                return String.format("%d:%02d:%02d", elapsedHours,
                        elapsedMinutes, elapsedSeconds);
            } else {
                return String.format("%02d:%02d",elapsedMinutes,
                        elapsedSeconds);
            }
        }
    }


}






