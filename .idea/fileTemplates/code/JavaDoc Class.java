 * @author wzp
 * @since ${DATE} ${TIME}
 * @version #parse("version.txt")
 
#foreach($param in $RECORD_COMPONENTS)
 * @param $param
#end
#foreach($param in $TYPE_PARAMS)
 * @param <$param>
#end