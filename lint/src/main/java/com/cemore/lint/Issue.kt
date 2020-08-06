package com.cemore.lint

import com.android.tools.lint.client.api.IssueRegistry
import com.android.tools.lint.detector.api.Category
import com.android.tools.lint.detector.api.Implementation
import com.android.tools.lint.detector.api.Issue
import com.android.tools.lint.detector.api.Scope

class Issue: IssueRegistry() {
    override val issues: List<Issue>
        get() = listOf(prefixIssue)

    private val prefixIssue = Issue.create(
        "Dosh Prefix",
        "Detects when we don't prefix necessary strings with dosh_",
        "Detects when we don't prefix necessary strings with dosh_",
        Implementation(
            DoshPrefixDetector::class.java,
            Scope.RESOURCE_FOLDER_SCOPE
        ),
        category = Category.USABILITY
    )


}