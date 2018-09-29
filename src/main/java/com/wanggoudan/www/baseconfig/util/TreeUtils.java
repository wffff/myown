package com.wanggoudan.www.baseconfig.util;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;

/**
 * Created by Adam.yao on 2017/11/10.
 */
public class TreeUtils {
    /**
     * 格式化list为树形list
     *
     * @param spread true 表示全部展开，其他 表示不展开
     */
    public static <T extends BaseTreeGrid> List<T> formatTree(List<T> list, boolean spread) {

        list.sort(Comparator.comparingInt(BaseTreeGrid::getSort));
        List<T> nodeList = new ArrayList<>();

        for (T node1 : list) {

            boolean mark = false;

            for (T node2 : list) {

                if (null != node1.getParentId() && node1.getParentId().equals(node2.getId())) {

                    node2.setLeaf(false);
                    mark = true;

                    if (null == node2.getChildren()) {
                        node2.setChildren(new ArrayList<>());
                    }

                    node2.getChildren().add(node1);
                    if (spread) node2.setSpread(true);

                    break;
                }
            }

            if (!mark) {
                nodeList.add(node1);
                if (spread) node1.setSpread(true);
            }
        }
        return nodeList;
    }


    public static class BaseTreeGrid<T extends BaseTreeGrid> implements Serializable {

        public Integer id;
        public Integer parentId;
        public String icon = "folder";
        public int sort; // 排序
        public int level = 0; // 层数
        public boolean leaf = true; // 是否为叶子节点，true表示是叶子节点，false表示不是叶子节点
        public boolean spread = false; // 是否展开，默认false,不展开
        public List<T> children; // 子节点列表

        private BaseTreeGrid() {
        }

        public BaseTreeGrid(Integer id, Integer parentId) {

            this.id = id;
            this.parentId = parentId;
        }

        public BaseTreeGrid(Integer id, Integer parentId, String icon, int sort, boolean spread) {

            this.id = id;
            this.parentId = parentId;
            this.icon = icon;
            this.sort = sort;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (!(o instanceof BaseTreeGrid)) return false;
            BaseTreeGrid<?> that = (BaseTreeGrid<?>) o;
            return sort == that.sort &&
                    level == that.level &&
                    leaf == that.leaf &&
                    spread == that.spread &&
                    Objects.equals(id, that.id) &&
                    Objects.equals(parentId, that.parentId) &&
                    Objects.equals(icon, that.icon) &&
                    Objects.equals(children, that.children);
        }

        @Override
        public int hashCode() {

            return Objects.hash(id, parentId, icon, sort, level, leaf, spread, children);
        }

        public Integer getId() {
            return id;
        }

        public void setId(Integer id) {
            this.id = id;
        }

        public Integer getParentId() {
            return parentId;
        }

        public void setParentId(Integer parentId) {
            this.parentId = parentId;
        }

        public String getIcon() {
            return icon;
        }

        public void setIcon(String icon) {
            this.icon = icon;
        }

        public int getSort() {
            return sort;
        }

        public void setSort(int sort) {
            this.sort = sort;
        }

        public int getLevel() {
            return level;
        }

        public void setLevel(int level) {
            this.level = level;
        }

        public boolean isLeaf() {
            return leaf;
        }

        public void setLeaf(boolean leaf) {
            this.leaf = leaf;
        }

        public boolean isSpread() {
            return spread;
        }

        public void setSpread(boolean spread) {
            this.spread = spread;
        }

        public List<T> getChildren() {
            return children;
        }

        public void setChildren(List<T> children) {
            this.children = children;
        }
    }
}