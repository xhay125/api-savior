package cn.gudqs7.plugins.common.util.structure;

import com.intellij.openapi.project.Project;
import com.intellij.psi.*;
import com.intellij.psi.impl.source.tree.java.PsiReferenceExpressionImpl;
import com.intellij.psi.search.GlobalSearchScope;
import org.jetbrains.annotations.NotNull;

import java.lang.reflect.Field;

/**
 * @author wq
 */
public class BaseTypeParseUtil {


    public static boolean parseBoolean(String value, boolean defaultVal) {
        try {
            return Boolean.parseBoolean(value);
        } catch (Exception ignored) {
            return defaultVal;
        }
    }

    public static Integer parseInt(String value, Integer defaultVal) {
        try {
            return Integer.parseInt(value);
        } catch (Exception ignored) {
            return defaultVal;
        }
    }

    /**
     * 通过字符串常量确定值的类型是什么
     *
     * @param <T>
     * @param psiReferenceExpression 字段名称
     * @return
     */
    public static Integer parseInt(PsiReferenceExpression psiReferenceExpression) {
        Project project = psiReferenceExpression.getProject();
        String clazzName = ((PsiReferenceExpressionImpl) (psiReferenceExpression.getFirstChild()).getReference()).getQualifiedName();
        String fieldName = psiReferenceExpression.getLastChild().getText();
        PsiClass aClass = JavaPsiFacade.getInstance(project).findClass(clazzName, GlobalSearchScope.allScope(project));
        PsiField fieldByName = aClass.findFieldByName(fieldName, false);
        PsiExpression initializer = fieldByName.getInitializer();
        return Integer.parseInt( initializer.getText());
    }
}
