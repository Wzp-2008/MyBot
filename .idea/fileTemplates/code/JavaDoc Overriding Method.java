 * @author wzp
 * @since ${DATE} ${TIME} v#parse("version.txt")
 
#foreach ($param in $PARAMS_INHERITED)
 * @param $param
#end
#if (!$PARAMS_INHERITED)
  #foreach ($param in $PARAMS)
 * @param $param
  #end
#end
#if($RETURN_TYPE != "void")
 * @return
#end
#foreach($param in $TYPE_PARAMS)
 * @param <$param>
#end
#foreach($exception in $THROWS)
 * @throws $exception
#end