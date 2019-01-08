public class MainActivity extends AppCompatActivity {
    //Guardar e ler registros do banco de dados
    private DatabaseReference reference = FirebaseDatabase.getInstance().getReference();

    //Autorização
    private FirebaseAuth auth = FirebaseAuth.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
		
		//Guardar informações no banco de dados
		
        DatabaseReference usuarios = reference.child("usuarios");

        Usuario usuario = new Usuario();
        usuario.setNome("Pedro");
        usuario.setSobrenome("Henrique");
        usuario.setIdade(12);

        usuarios.push().setValue(usuario);
		
		//Como recuperar os dados
		reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Log.i("FIREBASE", dataSnapshot.getValue().toString());
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
		
		//Cadastrando usuários 
		auth.createUserWithEmailAndPassword("email@exemplo.com","senhaDoUsR234").addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    Log.i("CreateUser","SUCESSO AO CRIAR USUÁRIO");
                }else{
                    Log.i("CreateUser", "FALHA AO CRIAR USUÁRIO");
                }
            }
        });
		
		//Verifica se o usuário está logado
        if(auth.getCurrentUser() != ){
            Log.i("CurrentUser", "USUÁRIO LOGADO");
        }else{
            Log.i("CurrentUser", "USUÁRIO NÃO ESTÁ LOGADO");
        }
		
		//deslogar usuário
		auth.signOut();
		
		//LOGANDO USUÁRIO
		auth.signInWithEmailAndPassword("email@exemplo.com","senhaDoUsR123").addOnCompleteListener(MainActivity.this,
                new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            Log.i("Sigin","USUÁRIO LOGADO");
                        }else{
                            Log.i("Sigin","USUÁRIO NÃO LOGADO");
                        }
                    }
                });
    }
}
