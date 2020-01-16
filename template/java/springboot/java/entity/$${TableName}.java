/*
 * @Author: Fantasy
 * @Date: 2020-01-13 15:38:35
 * @LastEditors  : Fantasy
 * @LastEditTime : 2020-01-16 10:31:43
 * @Descripttion: 
 * @Email: 776474961@qq.com
 */
package $${basePackage}.entity;

public class $${TableName} {
	@{
		private $${columnType} $${columnName}; // $${comment}
	}@
	
	@{
		public $${columnType} get$${ColumnName}() {
			return $${columnName};
		}
		public void set$${ColumnName}($${columnType} $${columnName}) {
			this.$${columnName} = $${columnName};
		}
	}@
}
