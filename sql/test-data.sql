DELETE FROM `DEPARTMENT`;
INSERT INTO `DEPARTMENT` (`dept_name`) VALUES ("定额所");
INSERT INTO `DEPARTMENT` (`dept_name`) VALUES ("标准所");
INSERT INTO `DEPARTMENT` (`dept_name`) VALUES ("经管所");
INSERT INTO `DEPARTMENT` (`dept_name`) VALUES ("办公室");
INSERT INTO `DEPARTMENT` (`dept_name`) VALUES ("财务部");

SELECT @de_id:=id FROM DEPARTMENT WHERE dept_name="定额所";
SELECT @bz_id:=id FROM DEPARTMENT WHERE dept_name="标准所";
SELECT @jg_id:=id FROM DEPARTMENT WHERE dept_name="经管所";
DELETE FROM `EMPLOYEE`;
INSERT INTO `EMPLOYEE` (last_name, email, department_id)  VALUES (
  "王熙元","746451950@qq.com", @de_id
)
INSERT INTO `EMPLOYEE` (last_name, email, department_id)  VALUES (
  "刘永俊","lyj@163.com", @de_id
)
INSERT INTO `EMPLOYEE` (last_name, email, department_id)  VALUES (
  "王凯琳","wkl@163.com", @bz_id
)
INSERT INTO `EMPLOYEE` (last_name, email, department_id)  VALUES (
  "肖楠","xn@qq.com", @jg_id
)
