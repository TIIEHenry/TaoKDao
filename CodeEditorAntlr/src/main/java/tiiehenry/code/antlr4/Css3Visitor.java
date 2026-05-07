// Generated from D:/Android/Projects/TaoKDao/grammars-v4-master/css3\Css3.g4 by ANTLR 4.7.2
package tiiehenry.code.antlr4;
import org.antlr.v4.runtime.tree.ParseTreeVisitor;

/**
 * This interface defines a complete generic visitor for a parse tree produced
 * by {@link Css3Parser}.
 *
 * @param <T> The return type of the visit operation. Use {@link Void} for
 * operations with no return type.
 */
public interface Css3Visitor<T> extends ParseTreeVisitor<T> {
	/**
	 * Visit a parse tree produced by {@link Css3Parser#stylesheet}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitStylesheet(Css3Parser.StylesheetContext ctx);
	/**
	 * Visit a parse tree produced by the {@code goodCharset}
	 * labeled alternative in {@link Css3Parser#charset}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitGoodCharset(Css3Parser.GoodCharsetContext ctx);
	/**
	 * Visit a parse tree produced by the {@code badCharset}
	 * labeled alternative in {@link Css3Parser#charset}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBadCharset(Css3Parser.BadCharsetContext ctx);
	/**
	 * Visit a parse tree produced by the {@code goodImport}
	 * labeled alternative in {@link Css3Parser#imports}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitGoodImport(Css3Parser.GoodImportContext ctx);
	/**
	 * Visit a parse tree produced by the {@code badImport}
	 * labeled alternative in {@link Css3Parser#imports}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBadImport(Css3Parser.BadImportContext ctx);
	/**
	 * Visit a parse tree produced by the {@code goodNamespace}
	 * labeled alternative in {@link Css3Parser#namespace}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitGoodNamespace(Css3Parser.GoodNamespaceContext ctx);
	/**
	 * Visit a parse tree produced by the {@code badNamespace}
	 * labeled alternative in {@link Css3Parser#namespace}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBadNamespace(Css3Parser.BadNamespaceContext ctx);
	/**
	 * Visit a parse tree produced by {@link Css3Parser#namespacePrefix}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitNamespacePrefix(Css3Parser.NamespacePrefixContext ctx);
	/**
	 * Visit a parse tree produced by {@link Css3Parser#media}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitMedia(Css3Parser.MediaContext ctx);
	/**
	 * Visit a parse tree produced by {@link Css3Parser#mediaQueryList}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitMediaQueryList(Css3Parser.MediaQueryListContext ctx);
	/**
	 * Visit a parse tree produced by {@link Css3Parser#mediaQuery}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitMediaQuery(Css3Parser.MediaQueryContext ctx);
	/**
	 * Visit a parse tree produced by {@link Css3Parser#mediaType}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitMediaType(Css3Parser.MediaTypeContext ctx);
	/**
	 * Visit a parse tree produced by {@link Css3Parser#mediaExpression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitMediaExpression(Css3Parser.MediaExpressionContext ctx);
	/**
	 * Visit a parse tree produced by {@link Css3Parser#mediaFeature}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitMediaFeature(Css3Parser.MediaFeatureContext ctx);
	/**
	 * Visit a parse tree produced by {@link Css3Parser#page}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPage(Css3Parser.PageContext ctx);
	/**
	 * Visit a parse tree produced by {@link Css3Parser#pseudoPage}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPseudoPage(Css3Parser.PseudoPageContext ctx);
	/**
	 * Visit a parse tree produced by {@link Css3Parser#selectorGroup}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSelectorGroup(Css3Parser.SelectorGroupContext ctx);
	/**
	 * Visit a parse tree produced by {@link Css3Parser#selector}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSelector(Css3Parser.SelectorContext ctx);
	/**
	 * Visit a parse tree produced by {@link Css3Parser#combinator}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCombinator(Css3Parser.CombinatorContext ctx);
	/**
	 * Visit a parse tree produced by {@link Css3Parser#simpleSelectorSequence}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSimpleSelectorSequence(Css3Parser.SimpleSelectorSequenceContext ctx);
	/**
	 * Visit a parse tree produced by {@link Css3Parser#typeSelector}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitTypeSelector(Css3Parser.TypeSelectorContext ctx);
	/**
	 * Visit a parse tree produced by {@link Css3Parser#typeNamespacePrefix}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitTypeNamespacePrefix(Css3Parser.TypeNamespacePrefixContext ctx);
	/**
	 * Visit a parse tree produced by {@link Css3Parser#elementName}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitElementName(Css3Parser.ElementNameContext ctx);
	/**
	 * Visit a parse tree produced by {@link Css3Parser#universal}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitUniversal(Css3Parser.UniversalContext ctx);
	/**
	 * Visit a parse tree produced by {@link Css3Parser#className}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitClassName(Css3Parser.ClassNameContext ctx);
	/**
	 * Visit a parse tree produced by {@link Css3Parser#attrib}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAttrib(Css3Parser.AttribContext ctx);
	/**
	 * Visit a parse tree produced by {@link Css3Parser#pseudo}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPseudo(Css3Parser.PseudoContext ctx);
	/**
	 * Visit a parse tree produced by {@link Css3Parser#functionalPseudo}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFunctionalPseudo(Css3Parser.FunctionalPseudoContext ctx);
	/**
	 * Visit a parse tree produced by {@link Css3Parser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExpression(Css3Parser.ExpressionContext ctx);
	/**
	 * Visit a parse tree produced by {@link Css3Parser#negation}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitNegation(Css3Parser.NegationContext ctx);
	/**
	 * Visit a parse tree produced by {@link Css3Parser#negationArg}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitNegationArg(Css3Parser.NegationArgContext ctx);
	/**
	 * Visit a parse tree produced by the {@code goodOperator}
	 * labeled alternative in {@link Css3Parser#operator}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitGoodOperator(Css3Parser.GoodOperatorContext ctx);
	/**
	 * Visit a parse tree produced by the {@code badOperator}
	 * labeled alternative in {@link Css3Parser#operator}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBadOperator(Css3Parser.BadOperatorContext ctx);
	/**
	 * Visit a parse tree produced by the {@code goodProperty}
	 * labeled alternative in {@link Css3Parser#property}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitGoodProperty(Css3Parser.GoodPropertyContext ctx);
	/**
	 * Visit a parse tree produced by the {@code badProperty}
	 * labeled alternative in {@link Css3Parser#property}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBadProperty(Css3Parser.BadPropertyContext ctx);
	/**
	 * Visit a parse tree produced by the {@code knownRuleset}
	 * labeled alternative in {@link Css3Parser#ruleset}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitKnownRuleset(Css3Parser.KnownRulesetContext ctx);
	/**
	 * Visit a parse tree produced by the {@code unknownRuleset}
	 * labeled alternative in {@link Css3Parser#ruleset}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitUnknownRuleset(Css3Parser.UnknownRulesetContext ctx);
	/**
	 * Visit a parse tree produced by {@link Css3Parser#declarationList}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDeclarationList(Css3Parser.DeclarationListContext ctx);
	/**
	 * Visit a parse tree produced by the {@code knownDeclaration}
	 * labeled alternative in {@link Css3Parser#declaration}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitKnownDeclaration(Css3Parser.KnownDeclarationContext ctx);
	/**
	 * Visit a parse tree produced by the {@code unknownDeclaration}
	 * labeled alternative in {@link Css3Parser#declaration}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitUnknownDeclaration(Css3Parser.UnknownDeclarationContext ctx);
	/**
	 * Visit a parse tree produced by {@link Css3Parser#prio}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPrio(Css3Parser.PrioContext ctx);
	/**
	 * Visit a parse tree produced by {@link Css3Parser#value}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitValue(Css3Parser.ValueContext ctx);
	/**
	 * Visit a parse tree produced by {@link Css3Parser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExpr(Css3Parser.ExprContext ctx);
	/**
	 * Visit a parse tree produced by the {@code knownTerm}
	 * labeled alternative in {@link Css3Parser#term}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitKnownTerm(Css3Parser.KnownTermContext ctx);
	/**
	 * Visit a parse tree produced by the {@code unknownTerm}
	 * labeled alternative in {@link Css3Parser#term}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitUnknownTerm(Css3Parser.UnknownTermContext ctx);
	/**
	 * Visit a parse tree produced by the {@code badTerm}
	 * labeled alternative in {@link Css3Parser#term}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBadTerm(Css3Parser.BadTermContext ctx);
	/**
	 * Visit a parse tree produced by {@link Css3Parser#function}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFunction(Css3Parser.FunctionContext ctx);
	/**
	 * Visit a parse tree produced by {@link Css3Parser#dxImageTransform}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDxImageTransform(Css3Parser.DxImageTransformContext ctx);
	/**
	 * Visit a parse tree produced by {@link Css3Parser#hexcolor}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitHexcolor(Css3Parser.HexcolorContext ctx);
	/**
	 * Visit a parse tree produced by {@link Css3Parser#number}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitNumber(Css3Parser.NumberContext ctx);
	/**
	 * Visit a parse tree produced by {@link Css3Parser#percentage}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPercentage(Css3Parser.PercentageContext ctx);
	/**
	 * Visit a parse tree produced by {@link Css3Parser#dimension}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDimension(Css3Parser.DimensionContext ctx);
	/**
	 * Visit a parse tree produced by {@link Css3Parser#unknownDimension}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitUnknownDimension(Css3Parser.UnknownDimensionContext ctx);
	/**
	 * Visit a parse tree produced by {@link Css3Parser#any}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAny(Css3Parser.AnyContext ctx);
	/**
	 * Visit a parse tree produced by the {@code unknownAtRule}
	 * labeled alternative in {@link Css3Parser#atRule}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitUnknownAtRule(Css3Parser.UnknownAtRuleContext ctx);
	/**
	 * Visit a parse tree produced by {@link Css3Parser#atKeyword}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAtKeyword(Css3Parser.AtKeywordContext ctx);
	/**
	 * Visit a parse tree produced by {@link Css3Parser#unused}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitUnused(Css3Parser.UnusedContext ctx);
	/**
	 * Visit a parse tree produced by {@link Css3Parser#block}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBlock(Css3Parser.BlockContext ctx);
	/**
	 * Visit a parse tree produced by {@link Css3Parser#nestedStatement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitNestedStatement(Css3Parser.NestedStatementContext ctx);
	/**
	 * Visit a parse tree produced by {@link Css3Parser#groupRuleBody}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitGroupRuleBody(Css3Parser.GroupRuleBodyContext ctx);
	/**
	 * Visit a parse tree produced by {@link Css3Parser#supportsRule}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSupportsRule(Css3Parser.SupportsRuleContext ctx);
	/**
	 * Visit a parse tree produced by {@link Css3Parser#supportsCondition}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSupportsCondition(Css3Parser.SupportsConditionContext ctx);
	/**
	 * Visit a parse tree produced by {@link Css3Parser#supportsConditionInParens}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSupportsConditionInParens(Css3Parser.SupportsConditionInParensContext ctx);
	/**
	 * Visit a parse tree produced by {@link Css3Parser#supportsNegation}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSupportsNegation(Css3Parser.SupportsNegationContext ctx);
	/**
	 * Visit a parse tree produced by {@link Css3Parser#supportsConjunction}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSupportsConjunction(Css3Parser.SupportsConjunctionContext ctx);
	/**
	 * Visit a parse tree produced by {@link Css3Parser#supportsDisjunction}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSupportsDisjunction(Css3Parser.SupportsDisjunctionContext ctx);
	/**
	 * Visit a parse tree produced by {@link Css3Parser#supportsDeclarationCondition}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSupportsDeclarationCondition(Css3Parser.SupportsDeclarationConditionContext ctx);
	/**
	 * Visit a parse tree produced by {@link Css3Parser#generalEnclosed}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitGeneralEnclosed(Css3Parser.GeneralEnclosedContext ctx);
	/**
	 * Visit a parse tree produced by {@link Css3Parser#var}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitVar(Css3Parser.VarContext ctx);
	/**
	 * Visit a parse tree produced by {@link Css3Parser#calc}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCalc(Css3Parser.CalcContext ctx);
	/**
	 * Visit a parse tree produced by {@link Css3Parser#calcSum}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCalcSum(Css3Parser.CalcSumContext ctx);
	/**
	 * Visit a parse tree produced by {@link Css3Parser#calcProduct}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCalcProduct(Css3Parser.CalcProductContext ctx);
	/**
	 * Visit a parse tree produced by {@link Css3Parser#calcValue}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCalcValue(Css3Parser.CalcValueContext ctx);
	/**
	 * Visit a parse tree produced by {@link Css3Parser#fontFaceRule}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFontFaceRule(Css3Parser.FontFaceRuleContext ctx);
	/**
	 * Visit a parse tree produced by the {@code knownFontFaceDeclaration}
	 * labeled alternative in {@link Css3Parser#fontFaceDeclaration}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitKnownFontFaceDeclaration(Css3Parser.KnownFontFaceDeclarationContext ctx);
	/**
	 * Visit a parse tree produced by the {@code unknownFontFaceDeclaration}
	 * labeled alternative in {@link Css3Parser#fontFaceDeclaration}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitUnknownFontFaceDeclaration(Css3Parser.UnknownFontFaceDeclarationContext ctx);
	/**
	 * Visit a parse tree produced by {@link Css3Parser#keyframesRule}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitKeyframesRule(Css3Parser.KeyframesRuleContext ctx);
	/**
	 * Visit a parse tree produced by {@link Css3Parser#keyframesBlocks}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitKeyframesBlocks(Css3Parser.KeyframesBlocksContext ctx);
	/**
	 * Visit a parse tree produced by {@link Css3Parser#keyframeSelector}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitKeyframeSelector(Css3Parser.KeyframeSelectorContext ctx);
	/**
	 * Visit a parse tree produced by {@link Css3Parser#viewport}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitViewport(Css3Parser.ViewportContext ctx);
	/**
	 * Visit a parse tree produced by {@link Css3Parser#counterStyle}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCounterStyle(Css3Parser.CounterStyleContext ctx);
	/**
	 * Visit a parse tree produced by {@link Css3Parser#fontFeatureValuesRule}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFontFeatureValuesRule(Css3Parser.FontFeatureValuesRuleContext ctx);
	/**
	 * Visit a parse tree produced by {@link Css3Parser#fontFamilyNameList}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFontFamilyNameList(Css3Parser.FontFamilyNameListContext ctx);
	/**
	 * Visit a parse tree produced by {@link Css3Parser#fontFamilyName}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFontFamilyName(Css3Parser.FontFamilyNameContext ctx);
	/**
	 * Visit a parse tree produced by {@link Css3Parser#featureValueBlock}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFeatureValueBlock(Css3Parser.FeatureValueBlockContext ctx);
	/**
	 * Visit a parse tree produced by {@link Css3Parser#featureType}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFeatureType(Css3Parser.FeatureTypeContext ctx);
	/**
	 * Visit a parse tree produced by {@link Css3Parser#featureValueDefinition}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFeatureValueDefinition(Css3Parser.FeatureValueDefinitionContext ctx);
	/**
	 * Visit a parse tree produced by {@link Css3Parser#ident}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitIdent(Css3Parser.IdentContext ctx);
	/**
	 * Visit a parse tree produced by {@link Css3Parser#ws}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitWs(Css3Parser.WsContext ctx);
}