package ru.Kodrik.MyUiRestDbService.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.Kodrik.MyUiRestDbService.dao.StudyGroupRepository;
import ru.Kodrik.MyUiRestDbService.entity.StudyGroup;

@Controller
@RequiredArgsConstructor
public class StudyGroupController {

    private final StudyGroupRepository studyGroupRepository;

    @GetMapping("/listGroups")
    public String showGroupList(Model model) {
        model.addAttribute("groups", studyGroupRepository.findAll());
        return "list-groups";
    }

    @GetMapping("/addGroupForm")
    public String showAddGroupForm(Model model) {
        model.addAttribute("group", new StudyGroup());
        return "add-group-form";
    }

    @PostMapping("/saveGroup")
    public String saveGroup(@ModelAttribute StudyGroup group) {
        studyGroupRepository.save(group);
        return "redirect:/listGroups";
    }

    @GetMapping("/showUpdateGroupForm")
    public String showUpdateGroupForm(@RequestParam Integer groupId, Model model) {
        StudyGroup group = studyGroupRepository.findById(groupId)
                .orElseThrow(() -> new IllegalArgumentException("Invalid group Id: " + groupId));
        model.addAttribute("group", group);
        return "add-group-form";
    }

    @GetMapping("/deleteGroup")
    public String deleteGroup(@RequestParam Integer groupId) {
        studyGroupRepository.deleteById(groupId);
        return "redirect:/listGroups";
    }
}