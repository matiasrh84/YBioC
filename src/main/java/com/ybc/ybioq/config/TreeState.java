package com.ybc.ybioq.config;

import javax.swing.*;
import javax.swing.tree.TreePath;


public class TreeState {

    public static void setTreeState(JTree tree, boolean expanded) {
        Object root = tree.getModel().getRoot();
        setTreeState(tree, new TreePath(root), expanded);
    }

    public static void setTreeState(JTree tree, TreePath path, boolean expanded) {
        Object lastNode = path.getLastPathComponent();
        for (int i = 0; i < tree.getModel().getChildCount(lastNode); i++) {
            Object child = tree.getModel().getChild(lastNode, i);
            TreePath pathToChild = path.pathByAddingChild(child);
            setTreeState(tree, pathToChild, expanded);
        }
        if (expanded)
            tree.expandPath(path);
        else
            tree.collapsePath(path);


    }
}
