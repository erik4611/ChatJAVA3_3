package server;

public class AuthDB implements AuthService {
    @Override
    public String getNicknameByLoginAndPassword(String login, String password) {
        return PreparesForDB.getNicknameByLoginAndPassword(login, password);
    }

    @Override
    public boolean registration(String login, String password, String nickname) {
        return PreparesForDB.registration(login, password, nickname);
    }


    @Override
    public boolean changeNick(String oldNickname, String newNickname) {
        return PreparesForDB.changeNick(oldNickname, newNickname);
    }
}
