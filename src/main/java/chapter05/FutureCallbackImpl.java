package chapter05;

import com.google.common.util.concurrent.FutureCallback;

import javax.annotation.Nullable;

public class FutureCallbackImpl implements FutureCallback<String> {

    private StringBuilder builder = new StringBuilder();

    @Override
    public void onSuccess(@Nullable String result) {
        System.out.println(">>> Receiving the result: " + result);
        builder.append(result).append(" successfully");
    }

    @Override
    public void onFailure(Throwable throwable) {
        builder.append(throwable.toString());
    }

    public String getCallbackResult() {
        return builder.toString();
    }
}
