package jsp.member.model;

import java.sql.Date;

public class MemberBean {//DTO data transfer object; 로직을 가지지 않는 순수한 데이터 객체로 getter, setter 메서드를 가진 MemberBean
						  //db에서 가져온 값 저장, 전달 (getter, setter 이용 )
	private String memberID;
    private String memberPWD;
    private String memberName;
    private String memberEmail;
    private Date memberReg;
    
    public String getMemberID() {
        return memberID;
    }
    public void setMemberID(String memberID) {
        this.memberID = memberID;
    }
    public String getMemberPWD() {
        return memberPWD;
    }
    public void setMemberPWD(String memberPWD) {
        this.memberPWD = memberPWD;
    }
    public String getMemberName() {
        return memberName;
    }
    public void setMemberName(String memberName) {
        this.memberName = memberName;
    }
    public String getMemberEmail() {
        return memberEmail;
    }
    public void setMemberEmail(String memberEmail) {
        this.memberEmail = memberEmail;
    }
    public Date getMemberReg() {
        return memberReg;
    }
    public void setMemberReg(Date memberReg) {
        this.memberReg = memberReg;
    }
    
    @Override
    public String toString() {
        return "MemberBean [memberID=" + memberID + ", memberPWD=" + memberPWD + ", memberName=" + memberName
                + ", memberEmail=" + memberEmail + ", memberReg=" + memberReg + "]";
    }
    
    
    
}