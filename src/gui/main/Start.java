package gui.main;

public class Start {
	public static void main(String[] args){
		Frame frame;
		try{
			frame = new Frame();
			core.search.Search obj = new core.search.Search();
			frame.initSearch(obj.getCurrentConversations());
			frame.initEmptyChat();
		}catch(Exception e){
			e.printStackTrace();
			System.out.println("GUI could not instantiate");
		}
	}
	public void startSearch(){
		
	}
	public void startChat(){
		
	}
}
