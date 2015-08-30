package com.tigerspike.listener;

import com.tigerspike.entity.SolutionsWrapper;

public interface SolutionServiceModelListener {

    void onSolutionsFetchSuccess(SolutionsWrapper solutionsWrapperObject);
    void onSolutionsFetchError(String error);
}
