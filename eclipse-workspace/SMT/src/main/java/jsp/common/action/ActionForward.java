package jsp.common.action;
 
// 페이지 이동을 처리하기 위한 클래스; 이동할 화면 경로 정봐 && Redirect에 대한 정보
 
public class ActionForward {
    
    private boolean isRedirect = false;
    private String path = null; // 이동할 다음 화면 
    
    //Redirect 사용여부, false이면 Forward 사용
     
    public boolean isRedirect() {
        return isRedirect;
    }
    public void setRedirect(boolean isRedirect) {
        this.isRedirect = isRedirect;
    }

	public String getPath() {
		// TODO Auto-generated method stub
		return path;
	}
    public void setPath(String path) {
        this.path = path;
    }

}