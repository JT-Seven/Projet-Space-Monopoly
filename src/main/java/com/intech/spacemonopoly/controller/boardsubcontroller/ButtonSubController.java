package com.intech.spacemonopoly.controller.boardsubcontroller;

import com.intech.spacemonopoly.controller.BoardController;

public class ButtonSubController {

    private final BoardController boardController;

    public ButtonSubController(BoardController boardController) {
        this.boardController = boardController;
    }

    public void allButtonDisable() {
        boardController.getBtnSellCard().setDisable(true);
        boardController.getBtnThrowDice().setDisable(true);
        boardController.getBtnNextTurn().setDisable(true);
        boardController.getBtnProperty().setDisable(true);
        boardController.getBtnConfirm().setDisable(true);
        boardController.getBtnSettings().setDisable(true);
    }

    public void allButtonClickable() {
        boardController.getBtnSellCard().setDisable(false);
        boardController.getBtnThrowDice().setDisable(false);
        boardController.getBtnNextTurn().setDisable(false);
        boardController.getBtnProperty().setDisable(false);
        boardController.getBtnConfirm().setDisable(false);
        boardController.getBtnSettings().setDisable(false);
    }

    public void allCircleInvisible() {
        boardController.getCircleBtnExchange().setVisible(false);
        boardController.getCircleBtnProperty().setVisible(false);
        boardController.getCircleBtnNextTurn().setVisible(false);
        boardController.getCircleBtnProperty().setVisible(false);
        boardController.getCircleBtnThrowDice().setVisible(false);
    }
}
