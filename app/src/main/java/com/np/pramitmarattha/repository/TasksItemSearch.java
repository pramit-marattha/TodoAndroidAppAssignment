package com.np.pramitmarattha.repository;

import androidx.annotation.NonNull;

public class TasksItemSearch {
    public long todoListId = -1;
    public String searchKeyword;
    @NonNull
    public CompletionState completionState = CompletionState.NotSpecified;
    public enum CompletionState {
        NotSpecified,
        Complete,
        Incomplete
    }
}
