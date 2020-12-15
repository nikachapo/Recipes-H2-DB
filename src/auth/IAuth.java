package auth;

import com.sun.istack.internal.Nullable;
import model.User;

public interface IAuth {

    @Nullable
    User authenticate(AuthChoice authChoice);

}