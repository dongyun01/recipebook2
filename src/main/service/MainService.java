package main.service;

import java.io.IOException;

import com.sun.javafx.scene.EnteredExitedHandler;

import comments.dao.commentsDAO;
import common.CommonService;
import event.enterEvent;
import event.stageMove;
import main.Controller;
import recipe.dao.recipeDAO;
import registMember.registMemberController;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.PasswordField;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.Slider;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.stage.WindowEvent;
import javafx.util.Duration;
import login.dto.LoginDTO;

public class MainService {
	private Controller ctrl;
	private MainService msv;
	private Parent menuForm;
	private MediaPlayer MediaPlayer;
	private String foodname;
	private double progress;
	private String comment;
	private String commentS;
	private double x=0;
	private double y=0;
	public void setController(Controller ctrl) {
		this.ctrl = ctrl;
	}
	
	
	public void menuOpenForm(Parent loginForm,LoginDTO dto) {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("../../main/Menu.fxml"));
		FXMLLoader loader1 = new FXMLLoader(getClass().getResource("detailRecipe.fxml"));
		Parent menuForm;
		Parent detailRecipeForm;
		try {
			
			menuForm = loader.load();
			detailRecipeForm = loader1.load();
			ctrl.setMenuForm(menuForm);
			
			//영석 추가
			AnchorPane ap = (AnchorPane)menuForm.lookup("#ap");
			stageMove move = new stageMove(ap);
			
			
			
			Stage stage = new Stage();
			stage.initStyle(StageStyle.UNDECORATED);
			stage.setTitle("메뉴");
			stage.setScene(new Scene(menuForm));
			stage.show();
			recipeDAO dao = new recipeDAO();
			// search form 시작
			Button logout = (Button)menuForm.lookup("#logout");
			TextField idText = (TextField)loginForm.lookup("#idText");
			PasswordField pwText = (PasswordField)loginForm.lookup("#pwText");
			Button search = (Button)menuForm.lookup("#search");
			TextField searchtext = (TextField)menuForm.lookup("#searchtext");
			ListView<String> list = (ListView)menuForm.lookup("#list");
			ListView<String> rank = (ListView)menuForm.lookup("#rank");
			ListView<String> clist = (ListView<String>)detailRecipeForm.lookup("#clist");
			Button cbutton = (Button)detailRecipeForm.lookup("#cbutton");
			TextField ctext = (TextField)detailRecipeForm.lookup("#ctext");
			commentsDAO cdao = new commentsDAO();
			dao.ranklist(rank);
			search.setVisible(false);
			
			
			logout.setOnAction(event->{
				stage.hide(); // 메뉴 폼 숨기기
				
				Stage logStage = new Stage();
				logStage.initStyle(StageStyle.UNDECORATED); //영석 추가
				logStage.setScene(loginForm.getScene());
				logStage.show(); // 로그인 폼 보이기
				idText.setText(""); // 로그아웃 시 아이디 입력된 값 제거
				pwText.setText(""); // 로그아웃 시 비밀번호 입력된 값 제거
			});
			
			enterEvent ev = new enterEvent();
			ev.enterkey(searchtext,list,rank,dao);
			
			search.setOnAction(event->{
				// ArrayList<String> food = new ArrayList<String>();
				if(searchtext.getText().equals("")) {
					CommonService.Msg("검색할 내용을 입력해주세요.");
				}
				else {
					list.getItems().removeAll(list.getItems());
					rank.getItems().removeAll(rank.getItems());
					dao.searchClickf(searchtext,list);
					dao.searchClickg(searchtext,list);
					dao.ranklist(rank);
				}
				if(!searchtext.getText().equals("") && list.getItems().toString().equals("[]")) {
					CommonService.Msg("해당 검색내용이 존재하지 않습니다.");
				}
			});
			
			
			// detailRecipeForm로 넘어가는 구간
			
			Stage stage1 = new Stage();
			stage1.setScene(new Scene(detailRecipeForm));
			stage1.initStyle(StageStyle.UNDECORATED);
			
			ProgressBar pro = (ProgressBar)detailRecipeForm.lookup("#pro");
			Button play = (Button)detailRecipeForm.lookup("#play");
			Button stop = (Button)detailRecipeForm.lookup("#stop");
			Button pause = (Button)detailRecipeForm.lookup("#pause");
			TabPane tabp = (TabPane)detailRecipeForm.lookup("#tabp");
			MediaView media = (MediaView)detailRecipeForm.lookup("#media");
			Slider volume = (Slider)detailRecipeForm.lookup("#volume");
			Label timelabel = (Label)detailRecipeForm.lookup("#timelabel");
			Button cxbutton = (Button)detailRecipeForm.lookup("#cxbutton");
			Button close = (Button)detailRecipeForm.lookup("#close");
			ImageView nomedia = (ImageView)detailRecipeForm.lookup("#nomedia");
			AnchorPane ap2 = (AnchorPane)detailRecipeForm.lookup("#ap");
			nomedia.setVisible(false);
			
			
			
			list.setOnMouseClicked(new EventHandler<MouseEvent>() {
				
			     @Override 
			     public void handle(MouseEvent event) {
			          if(event.getClickCount()>1 && list.getSelectionModel().getSelectedItem()!=null) {
			        	  String array[] = list.getSelectionModel().getSelectedItems().toString().split("]");
						  String title = array[0].replace("[[", "");
						  title = title = title.replace("레시피", "").trim();
						  
						  
			        	  stage1.setTitle("["+title+"] 상세 레시피");
						  stage1.setScene(detailRecipeForm.getScene());
						  stage1.show();
						  
						  stageMove move = new stageMove(ap2);
						  // [foodname 레시피] 재료: gredient
						  TextArea detailrecipe = (TextArea)detailRecipeForm.lookup("#detailrecipe");
						  detailrecipe.setEditable(false);
						  foodname = title;
						  detailrecipe.setText(dao.detailRecipe(foodname));
							//comment
						  cdao.commentSet(foodname, clist);
						// media setting
						if(dao.mediaSet(foodname).equals("1") || dao.mediaSet(foodname).equals("null")) {
							media.setMediaPlayer(null);
							
							play.setDisable(true);
							pause.setDisable(true);
							stop.setDisable(true);
							nomedia.setVisible(true);
							if(pro.getProgress()!=0) {
							MediaPlayer.stop();
							}
						}else {
							
							play.setDisable(false);
							pause.setDisable(false);
							stop.setDisable(false);
							nomedia.setVisible(false);
							
						Media mediaSource = new Media(getClass().getResource(dao.mediaSet(foodname)).toString());
						MediaPlayer = new MediaPlayer(mediaSource); 
						media.setMediaPlayer(MediaPlayer);
						
						MediaPlayer.setOnReady(new Runnable() {
							@Override
							public void run() {
								// TODO Auto-generated method stub
								MediaPlayer.currentTimeProperty().addListener(new ChangeListener<Duration>() {

									@Override
									public void changed(ObservableValue<? extends Duration> observable, Duration oldValue,
											Duration newValue) {
										// TODO Auto-generated method stub
										progress = MediaPlayer.getCurrentTime().toSeconds()/MediaPlayer.getTotalDuration().toSeconds();
										pro.setProgress(progress);
										timelabel.setText(
			                                    (int)MediaPlayer.getCurrentTime().toSeconds() + "/" +
			                                          (int)MediaPlayer.getTotalDuration().toSeconds() +"sec"
			                                    );
			                              
			                              MediaPlayer.setVolume(volume.getValue()/100.0);
									}
								});
							}
						});
						
						MediaPlayer.setOnPlaying(()->{
							play.setDisable(true);
							pause.setDisable(false);
							stop.setDisable(false);
						});
						MediaPlayer.setOnPaused(()->{
							play.setDisable(false);
							pause.setDisable(true);
							stop.setDisable(false);
						});
						MediaPlayer.setOnStopped(()->{
							play.setDisable(false);
							pause.setDisable(false);
							stop.setDisable(false);
						});
			          }
			          }
			     }
			});
			
			rank.setOnMouseClicked(new EventHandler<MouseEvent>() {
			     @Override 
			     public void handle(MouseEvent event) {
			          if(event.getClickCount()>1 && rank.getSelectionModel().getSelectedItem()!=null) {
			        	  String array[] = rank.getSelectionModel().getSelectedItems().toString().split("]");
			        	  stage1.setTitle("["+array[1].concat("]")+"상세 레시피");
			        	  
						  stage1.setScene(detailRecipeForm.getScene());
						  stage1.show();
						  
						  stageMove move = new stageMove(ap2);

						  //[조회수 n회] 음식이름 
						  TextArea detailrecipe = (TextArea)detailRecipeForm.lookup("#detailrecipe");
						  detailrecipe.setEditable(false);
						  foodname = array[1].trim();
						  detailrecipe.setText(dao.detailRecipe(foodname));
						//comment
						  cdao.commentSet(foodname, clist);
						// media setting
						  if(dao.mediaSet(foodname).equals("1") || dao.mediaSet(foodname).equals("null")) {
							  media.setMediaPlayer(null);
								if(media.getMediaPlayer()==null) {
									//System.out.println();
									play.setDisable(true);
									pause.setDisable(true);
									stop.setDisable(true);
									nomedia.setVisible(true);
									if(pro.getProgress()!=0) {
									MediaPlayer.stop();
									}
								}
							}else {
								play.setDisable(false);
								pause.setDisable(false);
								stop.setDisable(false);
								nomedia.setVisible(false);
								
						// 여기서부터 media set
						Media mediaSource = new Media(getClass().getResource(dao.mediaSet(foodname)).toString());
						MediaPlayer = new MediaPlayer(mediaSource); 
						media.setMediaPlayer(MediaPlayer);
						
						
						MediaPlayer.setOnReady(new Runnable() {
							@Override
							public void run() {
								// TODO Auto-generated method stub
								MediaPlayer.currentTimeProperty().addListener(new ChangeListener<Duration>() {

									@Override
									public void changed(ObservableValue<? extends Duration> observable, Duration oldValue,
											Duration newValue) {
										// TODO Auto-generated method stub
										progress = MediaPlayer.getCurrentTime().toSeconds()/MediaPlayer.getTotalDuration().toSeconds();
										pro.setProgress(progress);
										timelabel.setText(
			                                    (int)MediaPlayer.getCurrentTime().toSeconds() + "/" +
			                                          (int)MediaPlayer.getTotalDuration().toSeconds() +"sec"
			                                    );
			                              
			                              MediaPlayer.setVolume(volume.getValue()/100.0);
									}
								});
							}
						});
						
						MediaPlayer.setOnPlaying(()->{
							play.setDisable(true);
							pause.setDisable(false);
							stop.setDisable(false);
						});
						
						MediaPlayer.setOnPaused(()->{
							play.setDisable(false);
							pause.setDisable(true);
							stop.setDisable(false);
						});
						
						MediaPlayer.setOnStopped(()->{
							play.setDisable(false);
							pause.setDisable(false);
							stop.setDisable(false);
						});
						
			          } // else
			          }
			     }
			});

			play.setOnAction(event->{
				MediaPlayer.play();
			});
			
			stop.setOnAction(event->{
				MediaPlayer.stop();
			});
			
			pause.setOnAction(event->{
				MediaPlayer.pause();
			});
			
			
			close.setOnAction(event->{
				stage1.close();
				if(media.getMediaPlayer()!=null) {
				MediaPlayer.stop();
				}
				if(!clist.getItems().isEmpty()) {
					clist.getItems().removeAll(clist.getItems());
					ctext.setText("");
				}
			});
			
			clist.setOnMouseClicked(event->{
				if(!clist.getSelectionModel().isEmpty()) {
					//[작성자:dy] 안녕하세요.
				String array1[] = clist.getSelectionModel().getSelectedItems().toString().split("]");
				
				comment = array1[0].replace("[", "");
				comment = comment.replace("작성자: ", "");
				commentS = array1[1].replace("[", "");
				commentS = array1[1].replace("]", "");
				}
			});
			
			cxbutton.setOnAction(event->{
				if(!clist.getSelectionModel().isEmpty()) {
					if(!dto.getId().equals(comment)) {
						CommonService.Msg("작성하신 댓글만 삭제 가능합니다");
					}else {
						cdao.removeComment(foodname,comment,commentS,clist);
					}
				}else {
					CommonService.Msg("삭제할 내용을 선택하세요");
				}
			});
			
			ctext.addEventFilter(KeyEvent.KEY_PRESSED,event->{
				if(event.getCode() == KeyCode.ENTER) {
					if(ctext.getText().equals("")) {
						CommonService.Msg("댓글 내용을 입력해주세요.");
					}
					else {
						CommonService.Msg("댓글 등록이 완료되었습니다.");
						clist.getItems().removeAll(clist.getItems());
						cdao.insertComment(foodname,ctext,dto);
						cdao.commentSet(foodname, clist);
						ctext.setText("");
					}
				}
			});
			// comment limit
			ctext.setOnKeyPressed(event->{
				if(ctext.getLength()>69) {
					ctext.setText("");
					CommonService.Msg("글자수 제한입니다.");
				}
			});
			//comment
			cbutton.setOnAction(event->{
				if(ctext.getText().equals("")) {
					CommonService.Msg("댓글 내용을 입력해주세요.");
				}
				else {
					CommonService.Msg("댓글 등록이 완료되었습니다.");
					clist.getItems().removeAll(clist.getItems());
					cdao.insertComment(foodname,ctext,dto);
					cdao.commentSet(foodname, clist);
					ctext.clear();
				}
			});
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	public void regOpenForm(Parent loginForm) {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("../../registMember/registMember.fxml"));
		
		try {
			Parent regForm = loader.load();
			registMemberController regCtrl = loader.getController();
			regCtrl.setRegForm(regForm);
			regCtrl.setLogForm(loginForm);
			
			AnchorPane ap = (AnchorPane)regForm.lookup("#ap");
			stageMove move = new stageMove(ap);
			
			Stage regStage = new Stage();
			regStage.initStyle(StageStyle.UNDECORATED);
			regStage.setScene(new Scene(regForm));
			regStage.setTitle("회원 가입");
			regStage.show();
			
			Button cancel = (Button)regForm.lookup("#cancel");
			
			cancel.setOnAction(event->{
				regStage.hide();
				Stage logStage = new Stage();
				logStage.initStyle(StageStyle.UNDECORATED);
				logStage.setScene(loginForm.getScene());
				logStage.show();
			});

			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	public void adminMenuOpenForm(Parent loginForm) {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("../../adminMenu/adminMenu.fxml"));
		Parent adminMenuForm;
		
		try {
			adminMenuForm = loader.load();
			ctrl.setMenuForm(adminMenuForm);
			Stage stage = new Stage();
			
			//영석 수정
			AnchorPane ap = (AnchorPane)adminMenuForm.lookup("#ap");
			stageMove move = new stageMove(ap); 
			
			stage.initStyle(StageStyle.UNDECORATED);
			//까지
			stage.setTitle("관리자 메뉴");
			stage.setScene(new Scene(adminMenuForm));
			stage.show();
			
			Button logout = (Button)adminMenuForm.lookup("#logout");
			TextField idText = (TextField)loginForm.lookup("#idText");
			PasswordField pwText = (PasswordField)loginForm.lookup("#pwText");
			
			logout.setOnAction(event->{
				stage.hide(); // 메뉴 폼 숨기기
				Stage logStage = new Stage();
				logStage.setScene(loginForm.getScene());
				logStage.initStyle(StageStyle.UNDECORATED);
				logStage.setTitle("Recipe_Book");
				logStage.show(); // 로그인 폼 보이기
				idText.setText(""); // 로그아웃 시 아이디 입력된 값 제거
				pwText.setText(""); // 로그아웃 시 비밀번호 입력된 값 제거
			});
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	

}
