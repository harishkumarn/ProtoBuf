const grpc = require("grpc");
var protoLoader = require("@grpc/proto-loader");

const PROTO_PATH = "./proto/unary.proto";

var packageDefinition = protoLoader.loadSync(PROTO_PATH);

const clientStub = grpc.loadPackageDefinition(packageDefinition).Server;

const client = new clientStub("localhost:1234",grpc.credentials.createInsecure());

client.getGreeting({language:"Eng",name:"Harish Kumar N"},(error, greeting)=>{
	if(greeting)
	console.log(greeting);
	else
	console.log(error);
});
