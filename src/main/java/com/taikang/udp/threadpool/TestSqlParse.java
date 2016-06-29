package com.taikang.udp.threadpool;

import net.sf.jsqlparser.JSQLParserException;
import net.sf.jsqlparser.parser.CCJSqlParserManager;

import org.springframework.beans.BeanUtils;

public class TestSqlParse {
	public static void main(String[] args) throws JSQLParserException {
		CCJSqlParserManager pm = new CCJSqlParserManager();
		//String sql = "select a.BATCH_NO, a.company, a.branch, a.aracde ,b.teamnum,b.teamname , b.collector, b.collector_name,b.DTETRM,a.makeTime, b.rdtetrm ,a.MODIFYTIME,a.cur_step ,a.HANDOVER_TYPE from COLLECTOR_DTETRM_STEP a , collectorinfo b  where a.collector=b.collector and a.company=b.company and a.branch= b.branch and TRIM(a.cur_step) in('JA','SQ')   AND TRIM(A.COMPANY) = 'V'   AND  TRIM(A.BRANCH) = '10'    AND  TRIM(A.OPERATOR) = 'zyt'    order by a.makeTime desc";
		String sql = "select a.BATCH_NO, a.company, a.branch, a.aracde ,b.teamnum,b.teamname , b.collector, b.collector_name,b.DTETRM,a.makeTime, b.rdtetrm ,a.MODIFYTIME,a.cur_step from COLLECTOR_DTETRM_STEP a , collectorinfo b  where a.collector=b.collector and a.company=b.company and a.branch= b.branch and TRIM(a.cur_step)='TG' and b.rdtetrm <>'99999999'  AND TRIM(A.COMPANY) = 'V'    AND TRIM(A.BRANCH) = '10'     union select a.BATCH_NO, a.company, a.branch, a.aracde ,b.teamnum,b.teamname , b.collector, b.collector_name,b.DTETRM,a.makeTime, b.rdtetrm ,a.MODIFYTIME,a.cur_step from COLLECTOR_DTETRM_STEP a , collectorinfo b " +  
				 " where a.collector=b.collector and a.company=b.company and a.branch= b.branch and TRIM(a.cur_step)='TG' and b.rdtetrm <>'99999999'  AND TRIM(A.COMPANY) = 'V'    AND TRIM(A.BRANCH) = '10'" ; 
		
		/*String sql = " select t.prtno,  t.contno,  t.appntname, t.insuredname, to_char(t.polapplydate, 'yyyy-mm-dd') polapplydate " + 
		" from lccont t  where (t.uwflag not in ('1', '2', 'a') or  (t.uwflag in ('1', '2') and  to_date(to_char(t.uwdate, 'yyyy-mm-dd'), 'yyyy-mm-dd') >= " + 
		" add_months(sysdate, -1)) or (t.uwflag = 'a' and (select makedate " + 
		" from LCApplyRecallPol  where prtno = t.prtno  and rownum = 1) >= add_months(sysdate, -1))) " + 
		" and t.appflag = '0'  and t.bankagent in  (select a.agentcode from laagentex a where a.agentgroup = 'agentcode') order by polapplydate desc ";*/
		
		// 调用jsqlparser解析sql语句
		/*net.sf.jsqlparser.statement.Statement statement = pm .parse(new StringReader(sql));
		
		StringBuilder showSqlfrom = new StringBuilder(" ");
		StringBuilder showSqlwhere = new StringBuilder(" ");
		if (statement instanceof Select) {
			Select selectStatement = (Select) statement;
			try {
				
				net.sf.jsqlparser.statement.select.SetOperationList plainSelect1 =
						(net.sf.jsqlparser.statement.select.SetOperationList) selectStatement.getSelectBody();
			} catch (Exception e) {
				System.out.println("不含有union或其他联合查询");
				return;
			}
			List<WithItem> list = selectStatement.getWithItemsList();
			PlainSelect plainSelect = null;
			try {
				plainSelect = (PlainSelect) selectStatement.getSelectBody();
			} catch (Exception e) {
				System.out.println("含有union或其他联合查询");
				return;
			}

			Expression where = plainSelect.getWhere();
			List<Join> joins = plainSelect.getJoins();
			boolean oracleSiblings = plainSelect.isOracleSiblings();
			System.out.println(oracleSiblings);

			OracleHierarchicalExpression oracleHierarchical = plainSelect
					.getOracleHierarchical();
			List<Expression> groupByColumnReferences = plainSelect
					.getGroupByColumnReferences();
			List<OrderByElement> orderByElements = plainSelect
					.getOrderByElements();
			Expression having = plainSelect.getHaving();
			Limit limit = plainSelect.getLimit();

			if (plainSelect.getFromItem() != null) {
				showSqlfrom.append(" FROM ").append(plainSelect.getFromItem());
				if (joins != null) {
					Iterator<Join> it = joins.iterator();
					while (it.hasNext()) {
						Join join = it.next();
						if (join.isSimple()) {
							showSqlfrom.append(", ").append(join);
						} else {
							showSqlfrom.append(" ").append(join);
						}
					}
				}
				// sql += getFormatedList(joins, "", false, false);
				if (where != null) {
					showSqlwhere.append(" WHERE ").append(where);
				}
				if (oracleHierarchical != null) {
					showSqlwhere.append(oracleHierarchical.toString());
				}
				// 分组
				showSqlwhere.append(PlainSelect.getFormatedList(groupByColumnReferences, "GROUP BY"));
				if (having != null) {
					showSqlwhere.append(" HAVING ").append(having);
				}
			    // 排序
				showSqlwhere.append(PlainSelect.orderByToString(oracleSiblings,orderByElements));
				if (limit != null) {
					showSqlwhere.append(limit);
				}
			}
			//showSqlwhere.append(" WHERE ").append(plainSelect.getWhere());

			System.out.println(showSqlfrom.toString());
			System.out.println(showSqlwhere.toString());
			System.out.println(selectStatement.getSelectBody().toString());

			TablesNamesFinder tablesNamesFinder = new TablesNamesFinder();*/

			/*List tableList = tablesNamesFinder.getTableList(selectStatement);
			for (Iterator iter = tableList.iterator(); iter.hasNext();) {
				System.out.println(String.valueOf(iter.next()));
			}*/
			
			/*System.out.println(plainSelect.getWhere());
			System.out.println(plainSelect.getFromItem());*/
		//}
		//BeanUtils.copyProperties(obj, instance);
	}
}
