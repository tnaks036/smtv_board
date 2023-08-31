/**
 * 
 */
 
 function goBoard(){
	location.href="goBoard";
}

function insertBoard(){
	location.href="insertBoardFrm";
}

function updateFrm(boardID){
	location.href="updateFrm?board_ID="+boardID;
}

function deleteBoard(boardID){
	location.href="deleteBoard?board_ID="+boardID;
}

function BoardInfo(boardID){
	location.href="BoardInfo?board_ID="+boardID;
}

