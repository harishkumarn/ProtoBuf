const grpc = require("grpc");
var protoLoader = require("@grpc/proto-loader");

const PROTO_PATH = "./proto/miller.proto";

var packageDefinition = protoLoader.loadSync(PROTO_PATH);

const clientStub = grpc.loadPackageDefinition(packageDefinition).SpaceService;

const client = new clientStub("zhield-h23ctf-q5wwlco8.zohosecurity.team:443",grpc.credentials.createInsecure());

client.GetStationList({},(err,resp)=>{
	if(err) console.error(err);
	else console.log(resp);
});
