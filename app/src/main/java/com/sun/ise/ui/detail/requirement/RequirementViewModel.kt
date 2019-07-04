package com.sun.ise.ui.detail.requirement

import androidx.lifecycle.ViewModel
import com.sun.ise.data.model.Requirement
import com.sun.ise.data.remote.GetRequirementAsync
import com.sun.ise.data.repository.RequirementRepository

class RequirementViewModel(
    private val requirementRepository: RequirementRepository
) : ViewModel() {
    fun getRequirementsByEvent(eventId: Int): List<Requirement> {
        val getRequirementAsync = GetRequirementAsync()
        getRequirementAsync.execute(requirementRepository.getRequirementsByEvent(eventId))
        val requirementResult = getRequirementAsync.get()
        return requirementResult.requirements
    }
}
