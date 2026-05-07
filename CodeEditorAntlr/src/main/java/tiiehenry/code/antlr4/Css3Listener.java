// Generated from D:/Android/Projects/TaoKDao/grammars-v4-master/css3\Css3.g4 by ANTLR 4.7.2
package tiiehenry.code.antlr4;
import org.antlr.v4.runtime.tree.ParseTreeListener;

/**
 * This interface defines a complete listener for a parse tree produced by
 * {@link Css3Parser}.
 */
public interface Css3Listener extends ParseTreeListener {
	/**
	 * Enter a parse tree produced by {@link Css3Parser#stylesheet}.
	 * @param ctx the parse tree
	 */
	void enterStylesheet(Css3Parser.StylesheetContext ctx);
	/**
	 * Exit a parse tree produced by {@link Css3Parser#stylesheet}.
	 * @param ctx the parse tree
	 */
	void exitStylesheet(Css3Parser.StylesheetContext ctx);
	/**
	 * Enter a parse tree produced by the {@code goodCharset}
	 * labeled alternative in {@link Css3Parser#charset}.
	 * @param ctx the parse tree
	 */
	void enterGoodCharset(Css3Parser.GoodCharsetContext ctx);
	/**
	 * Exit a parse tree produced by the {@code goodCharset}
	 * labeled alternative in {@link Css3Parser#charset}.
	 * @param ctx the parse tree
	 */
	void exitGoodCharset(Css3Parser.GoodCharsetContext ctx);
	/**
	 * Enter a parse tree produced by the {@code badCharset}
	 * labeled alternative in {@link Css3Parser#charset}.
	 * @param ctx the parse tree
	 */
	void enterBadCharset(Css3Parser.BadCharsetContext ctx);
	/**
	 * Exit a parse tree produced by the {@code badCharset}
	 * labeled alternative in {@link Css3Parser#charset}.
	 * @param ctx the parse tree
	 */
	void exitBadCharset(Css3Parser.BadCharsetContext ctx);
	/**
	 * Enter a parse tree produced by the {@code goodImport}
	 * labeled alternative in {@link Css3Parser#imports}.
	 * @param ctx the parse tree
	 */
	void enterGoodImport(Css3Parser.GoodImportContext ctx);
	/**
	 * Exit a parse tree produced by the {@code goodImport}
	 * labeled alternative in {@link Css3Parser#imports}.
	 * @param ctx the parse tree
	 */
	void exitGoodImport(Css3Parser.GoodImportContext ctx);
	/**
	 * Enter a parse tree produced by the {@code badImport}
	 * labeled alternative in {@link Css3Parser#imports}.
	 * @param ctx the parse tree
	 */
	void enterBadImport(Css3Parser.BadImportContext ctx);
	/**
	 * Exit a parse tree produced by the {@code badImport}
	 * labeled alternative in {@link Css3Parser#imports}.
	 * @param ctx the parse tree
	 */
	void exitBadImport(Css3Parser.BadImportContext ctx);
	/**
	 * Enter a parse tree produced by the {@code goodNamespace}
	 * labeled alternative in {@link Css3Parser#namespace}.
	 * @param ctx the parse tree
	 */
	void enterGoodNamespace(Css3Parser.GoodNamespaceContext ctx);
	/**
	 * Exit a parse tree produced by the {@code goodNamespace}
	 * labeled alternative in {@link Css3Parser#namespace}.
	 * @param ctx the parse tree
	 */
	void exitGoodNamespace(Css3Parser.GoodNamespaceContext ctx);
	/**
	 * Enter a parse tree produced by the {@code badNamespace}
	 * labeled alternative in {@link Css3Parser#namespace}.
	 * @param ctx the parse tree
	 */
	void enterBadNamespace(Css3Parser.BadNamespaceContext ctx);
	/**
	 * Exit a parse tree produced by the {@code badNamespace}
	 * labeled alternative in {@link Css3Parser#namespace}.
	 * @param ctx the parse tree
	 */
	void exitBadNamespace(Css3Parser.BadNamespaceContext ctx);
	/**
	 * Enter a parse tree produced by {@link Css3Parser#namespacePrefix}.
	 * @param ctx the parse tree
	 */
	void enterNamespacePrefix(Css3Parser.NamespacePrefixContext ctx);
	/**
	 * Exit a parse tree produced by {@link Css3Parser#namespacePrefix}.
	 * @param ctx the parse tree
	 */
	void exitNamespacePrefix(Css3Parser.NamespacePrefixContext ctx);
	/**
	 * Enter a parse tree produced by {@link Css3Parser#media}.
	 * @param ctx the parse tree
	 */
	void enterMedia(Css3Parser.MediaContext ctx);
	/**
	 * Exit a parse tree produced by {@link Css3Parser#media}.
	 * @param ctx the parse tree
	 */
	void exitMedia(Css3Parser.MediaContext ctx);
	/**
	 * Enter a parse tree produced by {@link Css3Parser#mediaQueryList}.
	 * @param ctx the parse tree
	 */
	void enterMediaQueryList(Css3Parser.MediaQueryListContext ctx);
	/**
	 * Exit a parse tree produced by {@link Css3Parser#mediaQueryList}.
	 * @param ctx the parse tree
	 */
	void exitMediaQueryList(Css3Parser.MediaQueryListContext ctx);
	/**
	 * Enter a parse tree produced by {@link Css3Parser#mediaQuery}.
	 * @param ctx the parse tree
	 */
	void enterMediaQuery(Css3Parser.MediaQueryContext ctx);
	/**
	 * Exit a parse tree produced by {@link Css3Parser#mediaQuery}.
	 * @param ctx the parse tree
	 */
	void exitMediaQuery(Css3Parser.MediaQueryContext ctx);
	/**
	 * Enter a parse tree produced by {@link Css3Parser#mediaType}.
	 * @param ctx the parse tree
	 */
	void enterMediaType(Css3Parser.MediaTypeContext ctx);
	/**
	 * Exit a parse tree produced by {@link Css3Parser#mediaType}.
	 * @param ctx the parse tree
	 */
	void exitMediaType(Css3Parser.MediaTypeContext ctx);
	/**
	 * Enter a parse tree produced by {@link Css3Parser#mediaExpression}.
	 * @param ctx the parse tree
	 */
	void enterMediaExpression(Css3Parser.MediaExpressionContext ctx);
	/**
	 * Exit a parse tree produced by {@link Css3Parser#mediaExpression}.
	 * @param ctx the parse tree
	 */
	void exitMediaExpression(Css3Parser.MediaExpressionContext ctx);
	/**
	 * Enter a parse tree produced by {@link Css3Parser#mediaFeature}.
	 * @param ctx the parse tree
	 */
	void enterMediaFeature(Css3Parser.MediaFeatureContext ctx);
	/**
	 * Exit a parse tree produced by {@link Css3Parser#mediaFeature}.
	 * @param ctx the parse tree
	 */
	void exitMediaFeature(Css3Parser.MediaFeatureContext ctx);
	/**
	 * Enter a parse tree produced by {@link Css3Parser#page}.
	 * @param ctx the parse tree
	 */
	void enterPage(Css3Parser.PageContext ctx);
	/**
	 * Exit a parse tree produced by {@link Css3Parser#page}.
	 * @param ctx the parse tree
	 */
	void exitPage(Css3Parser.PageContext ctx);
	/**
	 * Enter a parse tree produced by {@link Css3Parser#pseudoPage}.
	 * @param ctx the parse tree
	 */
	void enterPseudoPage(Css3Parser.PseudoPageContext ctx);
	/**
	 * Exit a parse tree produced by {@link Css3Parser#pseudoPage}.
	 * @param ctx the parse tree
	 */
	void exitPseudoPage(Css3Parser.PseudoPageContext ctx);
	/**
	 * Enter a parse tree produced by {@link Css3Parser#selectorGroup}.
	 * @param ctx the parse tree
	 */
	void enterSelectorGroup(Css3Parser.SelectorGroupContext ctx);
	/**
	 * Exit a parse tree produced by {@link Css3Parser#selectorGroup}.
	 * @param ctx the parse tree
	 */
	void exitSelectorGroup(Css3Parser.SelectorGroupContext ctx);
	/**
	 * Enter a parse tree produced by {@link Css3Parser#selector}.
	 * @param ctx the parse tree
	 */
	void enterSelector(Css3Parser.SelectorContext ctx);
	/**
	 * Exit a parse tree produced by {@link Css3Parser#selector}.
	 * @param ctx the parse tree
	 */
	void exitSelector(Css3Parser.SelectorContext ctx);
	/**
	 * Enter a parse tree produced by {@link Css3Parser#combinator}.
	 * @param ctx the parse tree
	 */
	void enterCombinator(Css3Parser.CombinatorContext ctx);
	/**
	 * Exit a parse tree produced by {@link Css3Parser#combinator}.
	 * @param ctx the parse tree
	 */
	void exitCombinator(Css3Parser.CombinatorContext ctx);
	/**
	 * Enter a parse tree produced by {@link Css3Parser#simpleSelectorSequence}.
	 * @param ctx the parse tree
	 */
	void enterSimpleSelectorSequence(Css3Parser.SimpleSelectorSequenceContext ctx);
	/**
	 * Exit a parse tree produced by {@link Css3Parser#simpleSelectorSequence}.
	 * @param ctx the parse tree
	 */
	void exitSimpleSelectorSequence(Css3Parser.SimpleSelectorSequenceContext ctx);
	/**
	 * Enter a parse tree produced by {@link Css3Parser#typeSelector}.
	 * @param ctx the parse tree
	 */
	void enterTypeSelector(Css3Parser.TypeSelectorContext ctx);
	/**
	 * Exit a parse tree produced by {@link Css3Parser#typeSelector}.
	 * @param ctx the parse tree
	 */
	void exitTypeSelector(Css3Parser.TypeSelectorContext ctx);
	/**
	 * Enter a parse tree produced by {@link Css3Parser#typeNamespacePrefix}.
	 * @param ctx the parse tree
	 */
	void enterTypeNamespacePrefix(Css3Parser.TypeNamespacePrefixContext ctx);
	/**
	 * Exit a parse tree produced by {@link Css3Parser#typeNamespacePrefix}.
	 * @param ctx the parse tree
	 */
	void exitTypeNamespacePrefix(Css3Parser.TypeNamespacePrefixContext ctx);
	/**
	 * Enter a parse tree produced by {@link Css3Parser#elementName}.
	 * @param ctx the parse tree
	 */
	void enterElementName(Css3Parser.ElementNameContext ctx);
	/**
	 * Exit a parse tree produced by {@link Css3Parser#elementName}.
	 * @param ctx the parse tree
	 */
	void exitElementName(Css3Parser.ElementNameContext ctx);
	/**
	 * Enter a parse tree produced by {@link Css3Parser#universal}.
	 * @param ctx the parse tree
	 */
	void enterUniversal(Css3Parser.UniversalContext ctx);
	/**
	 * Exit a parse tree produced by {@link Css3Parser#universal}.
	 * @param ctx the parse tree
	 */
	void exitUniversal(Css3Parser.UniversalContext ctx);
	/**
	 * Enter a parse tree produced by {@link Css3Parser#className}.
	 * @param ctx the parse tree
	 */
	void enterClassName(Css3Parser.ClassNameContext ctx);
	/**
	 * Exit a parse tree produced by {@link Css3Parser#className}.
	 * @param ctx the parse tree
	 */
	void exitClassName(Css3Parser.ClassNameContext ctx);
	/**
	 * Enter a parse tree produced by {@link Css3Parser#attrib}.
	 * @param ctx the parse tree
	 */
	void enterAttrib(Css3Parser.AttribContext ctx);
	/**
	 * Exit a parse tree produced by {@link Css3Parser#attrib}.
	 * @param ctx the parse tree
	 */
	void exitAttrib(Css3Parser.AttribContext ctx);
	/**
	 * Enter a parse tree produced by {@link Css3Parser#pseudo}.
	 * @param ctx the parse tree
	 */
	void enterPseudo(Css3Parser.PseudoContext ctx);
	/**
	 * Exit a parse tree produced by {@link Css3Parser#pseudo}.
	 * @param ctx the parse tree
	 */
	void exitPseudo(Css3Parser.PseudoContext ctx);
	/**
	 * Enter a parse tree produced by {@link Css3Parser#functionalPseudo}.
	 * @param ctx the parse tree
	 */
	void enterFunctionalPseudo(Css3Parser.FunctionalPseudoContext ctx);
	/**
	 * Exit a parse tree produced by {@link Css3Parser#functionalPseudo}.
	 * @param ctx the parse tree
	 */
	void exitFunctionalPseudo(Css3Parser.FunctionalPseudoContext ctx);
	/**
	 * Enter a parse tree produced by {@link Css3Parser#expression}.
	 * @param ctx the parse tree
	 */
	void enterExpression(Css3Parser.ExpressionContext ctx);
	/**
	 * Exit a parse tree produced by {@link Css3Parser#expression}.
	 * @param ctx the parse tree
	 */
	void exitExpression(Css3Parser.ExpressionContext ctx);
	/**
	 * Enter a parse tree produced by {@link Css3Parser#negation}.
	 * @param ctx the parse tree
	 */
	void enterNegation(Css3Parser.NegationContext ctx);
	/**
	 * Exit a parse tree produced by {@link Css3Parser#negation}.
	 * @param ctx the parse tree
	 */
	void exitNegation(Css3Parser.NegationContext ctx);
	/**
	 * Enter a parse tree produced by {@link Css3Parser#negationArg}.
	 * @param ctx the parse tree
	 */
	void enterNegationArg(Css3Parser.NegationArgContext ctx);
	/**
	 * Exit a parse tree produced by {@link Css3Parser#negationArg}.
	 * @param ctx the parse tree
	 */
	void exitNegationArg(Css3Parser.NegationArgContext ctx);
	/**
	 * Enter a parse tree produced by the {@code goodOperator}
	 * labeled alternative in {@link Css3Parser#operator}.
	 * @param ctx the parse tree
	 */
	void enterGoodOperator(Css3Parser.GoodOperatorContext ctx);
	/**
	 * Exit a parse tree produced by the {@code goodOperator}
	 * labeled alternative in {@link Css3Parser#operator}.
	 * @param ctx the parse tree
	 */
	void exitGoodOperator(Css3Parser.GoodOperatorContext ctx);
	/**
	 * Enter a parse tree produced by the {@code badOperator}
	 * labeled alternative in {@link Css3Parser#operator}.
	 * @param ctx the parse tree
	 */
	void enterBadOperator(Css3Parser.BadOperatorContext ctx);
	/**
	 * Exit a parse tree produced by the {@code badOperator}
	 * labeled alternative in {@link Css3Parser#operator}.
	 * @param ctx the parse tree
	 */
	void exitBadOperator(Css3Parser.BadOperatorContext ctx);
	/**
	 * Enter a parse tree produced by the {@code goodProperty}
	 * labeled alternative in {@link Css3Parser#property}.
	 * @param ctx the parse tree
	 */
	void enterGoodProperty(Css3Parser.GoodPropertyContext ctx);
	/**
	 * Exit a parse tree produced by the {@code goodProperty}
	 * labeled alternative in {@link Css3Parser#property}.
	 * @param ctx the parse tree
	 */
	void exitGoodProperty(Css3Parser.GoodPropertyContext ctx);
	/**
	 * Enter a parse tree produced by the {@code badProperty}
	 * labeled alternative in {@link Css3Parser#property}.
	 * @param ctx the parse tree
	 */
	void enterBadProperty(Css3Parser.BadPropertyContext ctx);
	/**
	 * Exit a parse tree produced by the {@code badProperty}
	 * labeled alternative in {@link Css3Parser#property}.
	 * @param ctx the parse tree
	 */
	void exitBadProperty(Css3Parser.BadPropertyContext ctx);
	/**
	 * Enter a parse tree produced by the {@code knownRuleset}
	 * labeled alternative in {@link Css3Parser#ruleset}.
	 * @param ctx the parse tree
	 */
	void enterKnownRuleset(Css3Parser.KnownRulesetContext ctx);
	/**
	 * Exit a parse tree produced by the {@code knownRuleset}
	 * labeled alternative in {@link Css3Parser#ruleset}.
	 * @param ctx the parse tree
	 */
	void exitKnownRuleset(Css3Parser.KnownRulesetContext ctx);
	/**
	 * Enter a parse tree produced by the {@code unknownRuleset}
	 * labeled alternative in {@link Css3Parser#ruleset}.
	 * @param ctx the parse tree
	 */
	void enterUnknownRuleset(Css3Parser.UnknownRulesetContext ctx);
	/**
	 * Exit a parse tree produced by the {@code unknownRuleset}
	 * labeled alternative in {@link Css3Parser#ruleset}.
	 * @param ctx the parse tree
	 */
	void exitUnknownRuleset(Css3Parser.UnknownRulesetContext ctx);
	/**
	 * Enter a parse tree produced by {@link Css3Parser#declarationList}.
	 * @param ctx the parse tree
	 */
	void enterDeclarationList(Css3Parser.DeclarationListContext ctx);
	/**
	 * Exit a parse tree produced by {@link Css3Parser#declarationList}.
	 * @param ctx the parse tree
	 */
	void exitDeclarationList(Css3Parser.DeclarationListContext ctx);
	/**
	 * Enter a parse tree produced by the {@code knownDeclaration}
	 * labeled alternative in {@link Css3Parser#declaration}.
	 * @param ctx the parse tree
	 */
	void enterKnownDeclaration(Css3Parser.KnownDeclarationContext ctx);
	/**
	 * Exit a parse tree produced by the {@code knownDeclaration}
	 * labeled alternative in {@link Css3Parser#declaration}.
	 * @param ctx the parse tree
	 */
	void exitKnownDeclaration(Css3Parser.KnownDeclarationContext ctx);
	/**
	 * Enter a parse tree produced by the {@code unknownDeclaration}
	 * labeled alternative in {@link Css3Parser#declaration}.
	 * @param ctx the parse tree
	 */
	void enterUnknownDeclaration(Css3Parser.UnknownDeclarationContext ctx);
	/**
	 * Exit a parse tree produced by the {@code unknownDeclaration}
	 * labeled alternative in {@link Css3Parser#declaration}.
	 * @param ctx the parse tree
	 */
	void exitUnknownDeclaration(Css3Parser.UnknownDeclarationContext ctx);
	/**
	 * Enter a parse tree produced by {@link Css3Parser#prio}.
	 * @param ctx the parse tree
	 */
	void enterPrio(Css3Parser.PrioContext ctx);
	/**
	 * Exit a parse tree produced by {@link Css3Parser#prio}.
	 * @param ctx the parse tree
	 */
	void exitPrio(Css3Parser.PrioContext ctx);
	/**
	 * Enter a parse tree produced by {@link Css3Parser#value}.
	 * @param ctx the parse tree
	 */
	void enterValue(Css3Parser.ValueContext ctx);
	/**
	 * Exit a parse tree produced by {@link Css3Parser#value}.
	 * @param ctx the parse tree
	 */
	void exitValue(Css3Parser.ValueContext ctx);
	/**
	 * Enter a parse tree produced by {@link Css3Parser#expr}.
	 * @param ctx the parse tree
	 */
	void enterExpr(Css3Parser.ExprContext ctx);
	/**
	 * Exit a parse tree produced by {@link Css3Parser#expr}.
	 * @param ctx the parse tree
	 */
	void exitExpr(Css3Parser.ExprContext ctx);
	/**
	 * Enter a parse tree produced by the {@code knownTerm}
	 * labeled alternative in {@link Css3Parser#term}.
	 * @param ctx the parse tree
	 */
	void enterKnownTerm(Css3Parser.KnownTermContext ctx);
	/**
	 * Exit a parse tree produced by the {@code knownTerm}
	 * labeled alternative in {@link Css3Parser#term}.
	 * @param ctx the parse tree
	 */
	void exitKnownTerm(Css3Parser.KnownTermContext ctx);
	/**
	 * Enter a parse tree produced by the {@code unknownTerm}
	 * labeled alternative in {@link Css3Parser#term}.
	 * @param ctx the parse tree
	 */
	void enterUnknownTerm(Css3Parser.UnknownTermContext ctx);
	/**
	 * Exit a parse tree produced by the {@code unknownTerm}
	 * labeled alternative in {@link Css3Parser#term}.
	 * @param ctx the parse tree
	 */
	void exitUnknownTerm(Css3Parser.UnknownTermContext ctx);
	/**
	 * Enter a parse tree produced by the {@code badTerm}
	 * labeled alternative in {@link Css3Parser#term}.
	 * @param ctx the parse tree
	 */
	void enterBadTerm(Css3Parser.BadTermContext ctx);
	/**
	 * Exit a parse tree produced by the {@code badTerm}
	 * labeled alternative in {@link Css3Parser#term}.
	 * @param ctx the parse tree
	 */
	void exitBadTerm(Css3Parser.BadTermContext ctx);
	/**
	 * Enter a parse tree produced by {@link Css3Parser#function}.
	 * @param ctx the parse tree
	 */
	void enterFunction(Css3Parser.FunctionContext ctx);
	/**
	 * Exit a parse tree produced by {@link Css3Parser#function}.
	 * @param ctx the parse tree
	 */
	void exitFunction(Css3Parser.FunctionContext ctx);
	/**
	 * Enter a parse tree produced by {@link Css3Parser#dxImageTransform}.
	 * @param ctx the parse tree
	 */
	void enterDxImageTransform(Css3Parser.DxImageTransformContext ctx);
	/**
	 * Exit a parse tree produced by {@link Css3Parser#dxImageTransform}.
	 * @param ctx the parse tree
	 */
	void exitDxImageTransform(Css3Parser.DxImageTransformContext ctx);
	/**
	 * Enter a parse tree produced by {@link Css3Parser#hexcolor}.
	 * @param ctx the parse tree
	 */
	void enterHexcolor(Css3Parser.HexcolorContext ctx);
	/**
	 * Exit a parse tree produced by {@link Css3Parser#hexcolor}.
	 * @param ctx the parse tree
	 */
	void exitHexcolor(Css3Parser.HexcolorContext ctx);
	/**
	 * Enter a parse tree produced by {@link Css3Parser#number}.
	 * @param ctx the parse tree
	 */
	void enterNumber(Css3Parser.NumberContext ctx);
	/**
	 * Exit a parse tree produced by {@link Css3Parser#number}.
	 * @param ctx the parse tree
	 */
	void exitNumber(Css3Parser.NumberContext ctx);
	/**
	 * Enter a parse tree produced by {@link Css3Parser#percentage}.
	 * @param ctx the parse tree
	 */
	void enterPercentage(Css3Parser.PercentageContext ctx);
	/**
	 * Exit a parse tree produced by {@link Css3Parser#percentage}.
	 * @param ctx the parse tree
	 */
	void exitPercentage(Css3Parser.PercentageContext ctx);
	/**
	 * Enter a parse tree produced by {@link Css3Parser#dimension}.
	 * @param ctx the parse tree
	 */
	void enterDimension(Css3Parser.DimensionContext ctx);
	/**
	 * Exit a parse tree produced by {@link Css3Parser#dimension}.
	 * @param ctx the parse tree
	 */
	void exitDimension(Css3Parser.DimensionContext ctx);
	/**
	 * Enter a parse tree produced by {@link Css3Parser#unknownDimension}.
	 * @param ctx the parse tree
	 */
	void enterUnknownDimension(Css3Parser.UnknownDimensionContext ctx);
	/**
	 * Exit a parse tree produced by {@link Css3Parser#unknownDimension}.
	 * @param ctx the parse tree
	 */
	void exitUnknownDimension(Css3Parser.UnknownDimensionContext ctx);
	/**
	 * Enter a parse tree produced by {@link Css3Parser#any}.
	 * @param ctx the parse tree
	 */
	void enterAny(Css3Parser.AnyContext ctx);
	/**
	 * Exit a parse tree produced by {@link Css3Parser#any}.
	 * @param ctx the parse tree
	 */
	void exitAny(Css3Parser.AnyContext ctx);
	/**
	 * Enter a parse tree produced by the {@code unknownAtRule}
	 * labeled alternative in {@link Css3Parser#atRule}.
	 * @param ctx the parse tree
	 */
	void enterUnknownAtRule(Css3Parser.UnknownAtRuleContext ctx);
	/**
	 * Exit a parse tree produced by the {@code unknownAtRule}
	 * labeled alternative in {@link Css3Parser#atRule}.
	 * @param ctx the parse tree
	 */
	void exitUnknownAtRule(Css3Parser.UnknownAtRuleContext ctx);
	/**
	 * Enter a parse tree produced by {@link Css3Parser#atKeyword}.
	 * @param ctx the parse tree
	 */
	void enterAtKeyword(Css3Parser.AtKeywordContext ctx);
	/**
	 * Exit a parse tree produced by {@link Css3Parser#atKeyword}.
	 * @param ctx the parse tree
	 */
	void exitAtKeyword(Css3Parser.AtKeywordContext ctx);
	/**
	 * Enter a parse tree produced by {@link Css3Parser#unused}.
	 * @param ctx the parse tree
	 */
	void enterUnused(Css3Parser.UnusedContext ctx);
	/**
	 * Exit a parse tree produced by {@link Css3Parser#unused}.
	 * @param ctx the parse tree
	 */
	void exitUnused(Css3Parser.UnusedContext ctx);
	/**
	 * Enter a parse tree produced by {@link Css3Parser#block}.
	 * @param ctx the parse tree
	 */
	void enterBlock(Css3Parser.BlockContext ctx);
	/**
	 * Exit a parse tree produced by {@link Css3Parser#block}.
	 * @param ctx the parse tree
	 */
	void exitBlock(Css3Parser.BlockContext ctx);
	/**
	 * Enter a parse tree produced by {@link Css3Parser#nestedStatement}.
	 * @param ctx the parse tree
	 */
	void enterNestedStatement(Css3Parser.NestedStatementContext ctx);
	/**
	 * Exit a parse tree produced by {@link Css3Parser#nestedStatement}.
	 * @param ctx the parse tree
	 */
	void exitNestedStatement(Css3Parser.NestedStatementContext ctx);
	/**
	 * Enter a parse tree produced by {@link Css3Parser#groupRuleBody}.
	 * @param ctx the parse tree
	 */
	void enterGroupRuleBody(Css3Parser.GroupRuleBodyContext ctx);
	/**
	 * Exit a parse tree produced by {@link Css3Parser#groupRuleBody}.
	 * @param ctx the parse tree
	 */
	void exitGroupRuleBody(Css3Parser.GroupRuleBodyContext ctx);
	/**
	 * Enter a parse tree produced by {@link Css3Parser#supportsRule}.
	 * @param ctx the parse tree
	 */
	void enterSupportsRule(Css3Parser.SupportsRuleContext ctx);
	/**
	 * Exit a parse tree produced by {@link Css3Parser#supportsRule}.
	 * @param ctx the parse tree
	 */
	void exitSupportsRule(Css3Parser.SupportsRuleContext ctx);
	/**
	 * Enter a parse tree produced by {@link Css3Parser#supportsCondition}.
	 * @param ctx the parse tree
	 */
	void enterSupportsCondition(Css3Parser.SupportsConditionContext ctx);
	/**
	 * Exit a parse tree produced by {@link Css3Parser#supportsCondition}.
	 * @param ctx the parse tree
	 */
	void exitSupportsCondition(Css3Parser.SupportsConditionContext ctx);
	/**
	 * Enter a parse tree produced by {@link Css3Parser#supportsConditionInParens}.
	 * @param ctx the parse tree
	 */
	void enterSupportsConditionInParens(Css3Parser.SupportsConditionInParensContext ctx);
	/**
	 * Exit a parse tree produced by {@link Css3Parser#supportsConditionInParens}.
	 * @param ctx the parse tree
	 */
	void exitSupportsConditionInParens(Css3Parser.SupportsConditionInParensContext ctx);
	/**
	 * Enter a parse tree produced by {@link Css3Parser#supportsNegation}.
	 * @param ctx the parse tree
	 */
	void enterSupportsNegation(Css3Parser.SupportsNegationContext ctx);
	/**
	 * Exit a parse tree produced by {@link Css3Parser#supportsNegation}.
	 * @param ctx the parse tree
	 */
	void exitSupportsNegation(Css3Parser.SupportsNegationContext ctx);
	/**
	 * Enter a parse tree produced by {@link Css3Parser#supportsConjunction}.
	 * @param ctx the parse tree
	 */
	void enterSupportsConjunction(Css3Parser.SupportsConjunctionContext ctx);
	/**
	 * Exit a parse tree produced by {@link Css3Parser#supportsConjunction}.
	 * @param ctx the parse tree
	 */
	void exitSupportsConjunction(Css3Parser.SupportsConjunctionContext ctx);
	/**
	 * Enter a parse tree produced by {@link Css3Parser#supportsDisjunction}.
	 * @param ctx the parse tree
	 */
	void enterSupportsDisjunction(Css3Parser.SupportsDisjunctionContext ctx);
	/**
	 * Exit a parse tree produced by {@link Css3Parser#supportsDisjunction}.
	 * @param ctx the parse tree
	 */
	void exitSupportsDisjunction(Css3Parser.SupportsDisjunctionContext ctx);
	/**
	 * Enter a parse tree produced by {@link Css3Parser#supportsDeclarationCondition}.
	 * @param ctx the parse tree
	 */
	void enterSupportsDeclarationCondition(Css3Parser.SupportsDeclarationConditionContext ctx);
	/**
	 * Exit a parse tree produced by {@link Css3Parser#supportsDeclarationCondition}.
	 * @param ctx the parse tree
	 */
	void exitSupportsDeclarationCondition(Css3Parser.SupportsDeclarationConditionContext ctx);
	/**
	 * Enter a parse tree produced by {@link Css3Parser#generalEnclosed}.
	 * @param ctx the parse tree
	 */
	void enterGeneralEnclosed(Css3Parser.GeneralEnclosedContext ctx);
	/**
	 * Exit a parse tree produced by {@link Css3Parser#generalEnclosed}.
	 * @param ctx the parse tree
	 */
	void exitGeneralEnclosed(Css3Parser.GeneralEnclosedContext ctx);
	/**
	 * Enter a parse tree produced by {@link Css3Parser#var}.
	 * @param ctx the parse tree
	 */
	void enterVar(Css3Parser.VarContext ctx);
	/**
	 * Exit a parse tree produced by {@link Css3Parser#var}.
	 * @param ctx the parse tree
	 */
	void exitVar(Css3Parser.VarContext ctx);
	/**
	 * Enter a parse tree produced by {@link Css3Parser#calc}.
	 * @param ctx the parse tree
	 */
	void enterCalc(Css3Parser.CalcContext ctx);
	/**
	 * Exit a parse tree produced by {@link Css3Parser#calc}.
	 * @param ctx the parse tree
	 */
	void exitCalc(Css3Parser.CalcContext ctx);
	/**
	 * Enter a parse tree produced by {@link Css3Parser#calcSum}.
	 * @param ctx the parse tree
	 */
	void enterCalcSum(Css3Parser.CalcSumContext ctx);
	/**
	 * Exit a parse tree produced by {@link Css3Parser#calcSum}.
	 * @param ctx the parse tree
	 */
	void exitCalcSum(Css3Parser.CalcSumContext ctx);
	/**
	 * Enter a parse tree produced by {@link Css3Parser#calcProduct}.
	 * @param ctx the parse tree
	 */
	void enterCalcProduct(Css3Parser.CalcProductContext ctx);
	/**
	 * Exit a parse tree produced by {@link Css3Parser#calcProduct}.
	 * @param ctx the parse tree
	 */
	void exitCalcProduct(Css3Parser.CalcProductContext ctx);
	/**
	 * Enter a parse tree produced by {@link Css3Parser#calcValue}.
	 * @param ctx the parse tree
	 */
	void enterCalcValue(Css3Parser.CalcValueContext ctx);
	/**
	 * Exit a parse tree produced by {@link Css3Parser#calcValue}.
	 * @param ctx the parse tree
	 */
	void exitCalcValue(Css3Parser.CalcValueContext ctx);
	/**
	 * Enter a parse tree produced by {@link Css3Parser#fontFaceRule}.
	 * @param ctx the parse tree
	 */
	void enterFontFaceRule(Css3Parser.FontFaceRuleContext ctx);
	/**
	 * Exit a parse tree produced by {@link Css3Parser#fontFaceRule}.
	 * @param ctx the parse tree
	 */
	void exitFontFaceRule(Css3Parser.FontFaceRuleContext ctx);
	/**
	 * Enter a parse tree produced by the {@code knownFontFaceDeclaration}
	 * labeled alternative in {@link Css3Parser#fontFaceDeclaration}.
	 * @param ctx the parse tree
	 */
	void enterKnownFontFaceDeclaration(Css3Parser.KnownFontFaceDeclarationContext ctx);
	/**
	 * Exit a parse tree produced by the {@code knownFontFaceDeclaration}
	 * labeled alternative in {@link Css3Parser#fontFaceDeclaration}.
	 * @param ctx the parse tree
	 */
	void exitKnownFontFaceDeclaration(Css3Parser.KnownFontFaceDeclarationContext ctx);
	/**
	 * Enter a parse tree produced by the {@code unknownFontFaceDeclaration}
	 * labeled alternative in {@link Css3Parser#fontFaceDeclaration}.
	 * @param ctx the parse tree
	 */
	void enterUnknownFontFaceDeclaration(Css3Parser.UnknownFontFaceDeclarationContext ctx);
	/**
	 * Exit a parse tree produced by the {@code unknownFontFaceDeclaration}
	 * labeled alternative in {@link Css3Parser#fontFaceDeclaration}.
	 * @param ctx the parse tree
	 */
	void exitUnknownFontFaceDeclaration(Css3Parser.UnknownFontFaceDeclarationContext ctx);
	/**
	 * Enter a parse tree produced by {@link Css3Parser#keyframesRule}.
	 * @param ctx the parse tree
	 */
	void enterKeyframesRule(Css3Parser.KeyframesRuleContext ctx);
	/**
	 * Exit a parse tree produced by {@link Css3Parser#keyframesRule}.
	 * @param ctx the parse tree
	 */
	void exitKeyframesRule(Css3Parser.KeyframesRuleContext ctx);
	/**
	 * Enter a parse tree produced by {@link Css3Parser#keyframesBlocks}.
	 * @param ctx the parse tree
	 */
	void enterKeyframesBlocks(Css3Parser.KeyframesBlocksContext ctx);
	/**
	 * Exit a parse tree produced by {@link Css3Parser#keyframesBlocks}.
	 * @param ctx the parse tree
	 */
	void exitKeyframesBlocks(Css3Parser.KeyframesBlocksContext ctx);
	/**
	 * Enter a parse tree produced by {@link Css3Parser#keyframeSelector}.
	 * @param ctx the parse tree
	 */
	void enterKeyframeSelector(Css3Parser.KeyframeSelectorContext ctx);
	/**
	 * Exit a parse tree produced by {@link Css3Parser#keyframeSelector}.
	 * @param ctx the parse tree
	 */
	void exitKeyframeSelector(Css3Parser.KeyframeSelectorContext ctx);
	/**
	 * Enter a parse tree produced by {@link Css3Parser#viewport}.
	 * @param ctx the parse tree
	 */
	void enterViewport(Css3Parser.ViewportContext ctx);
	/**
	 * Exit a parse tree produced by {@link Css3Parser#viewport}.
	 * @param ctx the parse tree
	 */
	void exitViewport(Css3Parser.ViewportContext ctx);
	/**
	 * Enter a parse tree produced by {@link Css3Parser#counterStyle}.
	 * @param ctx the parse tree
	 */
	void enterCounterStyle(Css3Parser.CounterStyleContext ctx);
	/**
	 * Exit a parse tree produced by {@link Css3Parser#counterStyle}.
	 * @param ctx the parse tree
	 */
	void exitCounterStyle(Css3Parser.CounterStyleContext ctx);
	/**
	 * Enter a parse tree produced by {@link Css3Parser#fontFeatureValuesRule}.
	 * @param ctx the parse tree
	 */
	void enterFontFeatureValuesRule(Css3Parser.FontFeatureValuesRuleContext ctx);
	/**
	 * Exit a parse tree produced by {@link Css3Parser#fontFeatureValuesRule}.
	 * @param ctx the parse tree
	 */
	void exitFontFeatureValuesRule(Css3Parser.FontFeatureValuesRuleContext ctx);
	/**
	 * Enter a parse tree produced by {@link Css3Parser#fontFamilyNameList}.
	 * @param ctx the parse tree
	 */
	void enterFontFamilyNameList(Css3Parser.FontFamilyNameListContext ctx);
	/**
	 * Exit a parse tree produced by {@link Css3Parser#fontFamilyNameList}.
	 * @param ctx the parse tree
	 */
	void exitFontFamilyNameList(Css3Parser.FontFamilyNameListContext ctx);
	/**
	 * Enter a parse tree produced by {@link Css3Parser#fontFamilyName}.
	 * @param ctx the parse tree
	 */
	void enterFontFamilyName(Css3Parser.FontFamilyNameContext ctx);
	/**
	 * Exit a parse tree produced by {@link Css3Parser#fontFamilyName}.
	 * @param ctx the parse tree
	 */
	void exitFontFamilyName(Css3Parser.FontFamilyNameContext ctx);
	/**
	 * Enter a parse tree produced by {@link Css3Parser#featureValueBlock}.
	 * @param ctx the parse tree
	 */
	void enterFeatureValueBlock(Css3Parser.FeatureValueBlockContext ctx);
	/**
	 * Exit a parse tree produced by {@link Css3Parser#featureValueBlock}.
	 * @param ctx the parse tree
	 */
	void exitFeatureValueBlock(Css3Parser.FeatureValueBlockContext ctx);
	/**
	 * Enter a parse tree produced by {@link Css3Parser#featureType}.
	 * @param ctx the parse tree
	 */
	void enterFeatureType(Css3Parser.FeatureTypeContext ctx);
	/**
	 * Exit a parse tree produced by {@link Css3Parser#featureType}.
	 * @param ctx the parse tree
	 */
	void exitFeatureType(Css3Parser.FeatureTypeContext ctx);
	/**
	 * Enter a parse tree produced by {@link Css3Parser#featureValueDefinition}.
	 * @param ctx the parse tree
	 */
	void enterFeatureValueDefinition(Css3Parser.FeatureValueDefinitionContext ctx);
	/**
	 * Exit a parse tree produced by {@link Css3Parser#featureValueDefinition}.
	 * @param ctx the parse tree
	 */
	void exitFeatureValueDefinition(Css3Parser.FeatureValueDefinitionContext ctx);
	/**
	 * Enter a parse tree produced by {@link Css3Parser#ident}.
	 * @param ctx the parse tree
	 */
	void enterIdent(Css3Parser.IdentContext ctx);
	/**
	 * Exit a parse tree produced by {@link Css3Parser#ident}.
	 * @param ctx the parse tree
	 */
	void exitIdent(Css3Parser.IdentContext ctx);
	/**
	 * Enter a parse tree produced by {@link Css3Parser#ws}.
	 * @param ctx the parse tree
	 */
	void enterWs(Css3Parser.WsContext ctx);
	/**
	 * Exit a parse tree produced by {@link Css3Parser#ws}.
	 * @param ctx the parse tree
	 */
	void exitWs(Css3Parser.WsContext ctx);
}