package services;

import org.testng.annotations.Test;

public class Main {
    @Test
    public static  void Main_Booking(){

        CreateTrelloBoard createTrelloBoard =new CreateTrelloBoard();
        String boardId = createTrelloBoard.postCreateBoard(" New Test Board");
        CreateCard createCard =new CreateCard();
        GetList getList =new GetList();
        String listId= getList.getaListonCreatedBoard(boardId);
        String cardId1= createCard.postCreateCard("Card1", listId,boardId);
        String cardId2= createCard.postCreateCard("Card2",listId,boardId);
        GetCard getCard = new GetCard();
        String cardIdrandomly = getCard.getaCard(listId);
        UpdateCard updateCard =new UpdateCard();
        updateCard.putUpdateCard(cardIdrandomly,"Updated");
        DeleteCard deleteCard =new DeleteCard();
        deleteCard.deleteCreatedCard(cardId1);
        deleteCard.deleteCreatedCard(cardId2);
        DeleteBoard deleteBoard =new DeleteBoard();
        deleteBoard.deleteCreatedBoard(boardId);

    }
}
